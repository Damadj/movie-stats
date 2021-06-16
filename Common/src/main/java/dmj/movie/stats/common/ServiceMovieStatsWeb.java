package dmj.movie.stats.common;

import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.objects.MovieStatsException;

import java.util.List;

public interface ServiceMovieStatsWeb {

    /** Get movie stats by guid */
    MovieDto find(String guid);

    /** Create/update movie stats */
    void update(MovieDto dto) throws MovieStatsException;

    /** Remove movie stats */
    void remove(String guid) throws MovieStatsException;

    /** Get all movie stats */
    List<MovieDto> lists() throws MovieStatsException;

    /** Dynamic search by movie name */
    List<MovieDto> search(String name) throws MovieStatsException;
}
