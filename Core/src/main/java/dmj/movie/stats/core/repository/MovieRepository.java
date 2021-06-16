package dmj.movie.stats.core.repository;

import dmj.movie.stats.common.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {

    @Query("SELECT t FROM Movie t WHERE t.guid = :GUID")
    Movie findMovieByGUID(@Param("GUID")String guid);

    @Query("SELECT t FROM Movie t WHERE lower(t.name) LIKE :name")
    List<Movie> findMovieByName(@Param("name") String name);
}
