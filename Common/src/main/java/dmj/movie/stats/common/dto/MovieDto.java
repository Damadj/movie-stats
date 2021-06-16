package dmj.movie.stats.common.dto;

import dmj.movie.stats.common.enums.Genre;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
public class MovieDto implements Serializable {

    private static final long serialVersionUID = -7159794871366728173L;


    private String guid;
    private String name;
    private LocalDate releaseDate;
    private LocalDate watchDate;
    private Genre genre = Genre.NONE;
    private Integer runtime;
    private String imageLink;
    private boolean series = false;
    private Integer episodes;

    public MovieDto() {
    }

    public MovieDto(MovieDto dto) {
        this.guid = dto.getGuid();
        this.name = dto.getName();
        this.releaseDate = dto.getReleaseDate();
        this.watchDate = dto.getWatchDate();
        this.genre = dto.getGenre();
        this.runtime = dto.getRuntime();
        this.imageLink = dto.getImageLink();
        this.series = dto.isSeries();
        this.episodes = dto.getEpisodes();
    }
}
