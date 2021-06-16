package dmj.movie.stats.adapter.rest;

import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.dto.MoviesDto;
import dmj.movie.stats.common.objects.MovieStatsException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
public class MovieStatsAdapterRest {
    private static final String web_context = "/v1/movie/stats";
    private static final String url_get_all = "/lists";
    private static final String url_get_one = "/find";
    private static final String url_update = "/update";
    private static final String url_remove = "/remove";
    private static final String url_dynamic_search = "/dynamic";

    @Value("${movie_stats_server:moviestats.reference}")
    private String server="";

    private final RestTemplate restTemplate = new RestTemplate();

    public MovieStatsAdapterRest() {

    }

    public String getServer() {
        return server;
    }
    public void setServer(String server) {
        this.server = server;
    }

    /** Get all movie stats */

    private List<MovieDto> getAll(String server) throws MovieStatsException{
        try {
            String url = "http://" + server + web_context + url_get_all;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            ResponseEntity<MoviesDto> data = restTemplate.getForEntity(
                    builder.toUriString(),
                    MoviesDto.class);
            return data.getBody().getMovies();
        } catch (Exception e) {
            throw new MovieStatsException(e.toString()).initCause(e);
        }
    }

    /** Get movie stats by guid */

    private MovieDto getOne(String server, String guid) throws MovieStatsException {
        try {
            String url = "http://" + server + web_context + url_get_one;
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("guid", guid);

            ResponseEntity<MovieDto> data = restTemplate.getForEntity(
                    builder.toUriString(),
                    MovieDto.class);
            return data.getBody();
        } catch (Exception e) {
            throw new MovieStatsException(e.toString()).initCause(e);
        }
    }

    /** Create/update movie stats */

    private void update(String server, MovieDto dto) throws MovieStatsException {
        try {
            String url = "http://" + server + web_context + url_update;

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Object> entity = new HttpEntity<>(dto, headers);

            restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.POST,
                    entity,
                    void.class);

        } catch (Exception e) {
            throw new MovieStatsException(e.toString()).initCause(e);
        }
    }

    /** Dynamic search by name */

    private List<MovieDto> search(String server, String name) throws MovieStatsException {
        try {
            String url = "http://" + server + web_context + url_dynamic_search;


            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("name", name);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            ResponseEntity<MoviesDto> data = restTemplate.exchange(
                    builder.build().toString(),
                    HttpMethod.GET,
                    entity,
                    MoviesDto.class);

            return data.getBody().getMovies();
        } catch (Exception e) {
            throw new MovieStatsException(e.toString()).initCause(e);
        }
    }

    /** Remove movie by guid */

    private void remove(String server, String guid) throws MovieStatsException {
        try {
            String url = "http://" + server + web_context + url_remove;

            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                    .queryParam("guid", guid);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            restTemplate.exchange(
                    builder.build().toString(),
                    HttpMethod.POST,
                    entity,
                    void.class);
        } catch (Exception e) {
            throw new MovieStatsException(e.toString()).initCause(e);
        }
    }

    public List<MovieDto> getAll() throws MovieStatsException {
        return getAll(server);
    }
    public MovieDto getOne(String guid) throws MovieStatsException {
        return getOne(server, guid);
    }
    public void update(MovieDto dto) throws MovieStatsException {
        update(server, dto);
    }
    public void remove(String guid) throws MovieStatsException {
        remove(server, guid);
    }
    public List<MovieDto> search(String name) throws MovieStatsException {
        return search(server, name);
    }
}