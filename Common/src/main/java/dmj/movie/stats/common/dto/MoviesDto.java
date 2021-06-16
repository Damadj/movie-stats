package dmj.movie.stats.common.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
public class MoviesDto implements Serializable {

    private static final long serialVersionUID = 9007920251869512161L;

    private List<MovieDto> movies;

    public MoviesDto() {}
    public MoviesDto(List<MovieDto> movies) {
        this.movies = movies;
    }
}
