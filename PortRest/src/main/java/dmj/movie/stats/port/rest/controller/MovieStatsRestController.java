package dmj.movie.stats.port.rest.controller;

import dmj.movie.stats.common.ServiceMovieStatsWeb;
import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.dto.MoviesDto;
import dmj.movie.stats.common.objects.MovieStatsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping(value = "/v1/movie/stats")
public class MovieStatsRestController {
    private ServiceMovieStatsWeb serviceMovieStatsWeb;

    public MovieStatsRestController(ServiceMovieStatsWeb serviceMovieStatsWeb) {
        this.serviceMovieStatsWeb = serviceMovieStatsWeb;
    }

    /** Find all movies */
    @GetMapping(value = "/lists", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MoviesDto> getAll() throws MovieStatsException {
        return ResponseEntity.ok(new MoviesDto(serviceMovieStatsWeb.lists()));
    }

    /** Find one movie */
    @GetMapping(value = "/find", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MovieDto> getOne(@RequestParam("guid") String guid) {
        return ResponseEntity.ok(serviceMovieStatsWeb.find(guid));
    }

    /** Create/update movie */
    @PostMapping(path = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity update(@RequestBody() MovieDto dto) {
        try {
            serviceMovieStatsWeb.update(dto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /** Remove movie */
    @PostMapping(path = "/remove", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity remove(@RequestParam("guid") String guid) {
        try {
            serviceMovieStatsWeb.remove(guid);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /** Dynamic search by name */
    @GetMapping(value = "/dynamic", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<MoviesDto> getClientByDynamicFio(@RequestParam("name") String name) {
        try {
            MoviesDto movies = new MoviesDto();
            movies.setMovies(serviceMovieStatsWeb.search(name));
            return ResponseEntity.ok(movies);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
