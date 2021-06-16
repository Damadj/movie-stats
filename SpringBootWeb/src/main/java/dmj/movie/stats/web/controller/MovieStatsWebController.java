package dmj.movie.stats.web.controller;

import dmj.movie.stats.adapter.rest.MovieStatsAdapterRest;
import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.objects.MovieStatsException;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@Component(value = "msController")
@Scope("session")
public class MovieStatsWebController {
	@Autowired
	MovieStatsAdapterRest msAdapter;


	private List<MovieDto> movies = new ArrayList<>();
	private MovieDto selectedMovie;
	private String searchText = "";
	private boolean disabled = true;
	private int count = 0;

	public void loadData() {
//		try {
//			movies = msAdapter.getAll();
//		} catch (MovieStatsException e) {
//			e.printStackTrace();
//		}
	}

	public void refreshData () {
		if (!searchText.isEmpty()) dynamicSearch();
		else search();
	}

	public void createMovie() {
		selectedMovie = new MovieDto();
		PrimeFaces.current().executeScript("PF('wv_ms').show();");
	}

	public void updateMovie(MovieDto dto) {
		selectedMovie = new MovieDto(dto);
		if (selectedMovie.isSeries()) disabled = false;
		PrimeFaces.current().executeScript("PF('wv_ms').show();");
	}

	public void saveMovie() {
		if (selectedMovie.getName() == null || selectedMovie.getRuntime() == null)
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", "Name/runtime is empty"));
		else {
			try {
				msAdapter.update(selectedMovie);
				refreshData();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", null));
			} catch (NumberFormatException e) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Failed", null));
			} catch (MovieStatsException e) {
				e.printStackTrace();
			}
			PrimeFaces.current().executeScript("PF('wv_ms').hide()");
			disabled = true;
		}
	}

	public void removeMovie(MovieDto dto) {
		selectedMovie = dto;
		PrimeFaces.current().executeScript("PF('wv_confirm_del').show();");
	}

	public void confirmRemoveMovie() {
		try {
			msAdapter.remove(selectedMovie.getGuid());
			PrimeFaces.current().executeScript("PF('wv_confirm_del').hide();");
			refreshData();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Success", "Movie was deleted"));
		} catch (MovieStatsException e) {
			e.printStackTrace();
		}
	}

	public void dynamicSearch() {
		try {
			if (searchText.length() >= 3) {
				movies = msAdapter.search(searchText.toLowerCase());
				movies.sort(Comparator.comparing(MovieDto::getName));
			} else {
				movies.clear();
			}
		} catch (MovieStatsException e) {
			e.printStackTrace();
		}
	}

	public void search() {
		try {
			movies = msAdapter.getAll();
			searchText = "";
			if (movies.isEmpty()) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Sorry", "No movies found"));
			else movies.sort(Comparator.comparing(MovieDto::getName));
		} catch (MovieStatsException e) {
			e.printStackTrace();
		}
	}

	public void changeMovieSeries() {
		disabled = !selectedMovie.isSeries();
	}

	public void refreshSeries() {
		disabled = true;
		PrimeFaces.current().executeScript("PF('wv_ms').hide()");
	}

	public void countSummaryWatchTime() {
		count = 0;
		try {
			List<MovieDto> moviesDto = msAdapter.getAll();
			moviesDto.forEach(dto -> {
				if (dto.isSeries()) count += dto.getRuntime() * dto.getEpisodes();
				count += dto.getRuntime();
			});
			PrimeFaces.current().executeScript("PF('wv_count').show()");
		} catch (MovieStatsException e) {
			e.printStackTrace();
		}
	}

	public String convertToYears(LocalDate date) {
		return date == null ? null : date.format(DateTimeFormatter.ofPattern("yyyy"));
	}
	public String convertToDate(LocalDate date) {
		return date == null ? null : date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	}

	public String convertToHours(int runtime) {
		return runtime / 60 + "h " + runtime % 60 + "min";
	}

	public String showMovieStats() {
		return "/moviestats/ms.xhtml?faces-redirect=true";
	}
}
