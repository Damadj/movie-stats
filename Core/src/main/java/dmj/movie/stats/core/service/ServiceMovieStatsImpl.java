package dmj.movie.stats.core.service;

import dmj.movie.stats.common.ServiceMovieStatsWeb;
import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.model.Movie;
import dmj.movie.stats.common.objects.MovieStatsException;
import dmj.movie.stats.core.repository.MovieRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service("serviceMovieStats")
@Transactional("movieTransactionManager")
public class ServiceMovieStatsImpl implements ServiceMovieStatsWeb {

    private final MovieRepository movieRepository;

    private final MapperFacade mapper;

    public ServiceMovieStatsImpl(MovieRepository movieRepository,
                                 @Qualifier("movieMapperFacade") MapperFacade mapper) {
        this.movieRepository = movieRepository;
        this.mapper = mapper;
    }

    @Override
    public MovieDto find(String guid) {
        return mapper.map(movieRepository.findMovieByGUID(guid), MovieDto.class);
    }

    @Override
    public void update(MovieDto dto) throws MovieStatsException {
        if (dto == null) throw new MovieStatsException("Не указан объект");
        Movie movie = mapper.map(dto, Movie.class);
        if (movie.getGuid() == null) movie.setGuid(UUID.randomUUID().toString());
        movieRepository.save(movie);
    }

    @Override
    public void remove(String guid) {
        movieRepository.deleteById(guid);
    }

    @Override
    public List<MovieDto> lists() throws MovieStatsException {
        try {
            return mapper.mapAsList(movieRepository.findAll(), MovieDto.class);
        } catch (Exception e) {
            throw new MovieStatsException(e);
        }
    }

    @Override
    public List<MovieDto> search(String name) throws MovieStatsException {
        try {
            return mapper.mapAsList(movieRepository.findMovieByName("%" + name + "%"), MovieDto.class);
        } catch (Exception e) {
            throw new MovieStatsException(e);
        }
    }
}
