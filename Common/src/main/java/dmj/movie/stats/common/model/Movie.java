package dmj.movie.stats.common.model;

import dmj.movie.stats.common.enums.Genre;
import dmj.movie.stats.common.util.LocalDateDatePersistenceConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Access(AccessType.FIELD)
@Table(name = "\"Movie\"")
public class Movie implements Serializable {

    private static final long serialVersionUID = -8623605103604210336L;

    @Id
    @Column(name = "\"GUID\"", nullable = false, length = 36)
    private String guid;
    @Column(name = "\"Name\"", nullable = false, length = 500)
    private String name;
    @Column(name = "\"ReleaseDate\"", nullable = true)
    @Convert(converter = LocalDateDatePersistenceConverter.class)
    private LocalDate releaseDate;
    @Column(name = "\"WatchDate\"", nullable = true)
    @Convert(converter = LocalDateDatePersistenceConverter.class)
    private LocalDate watchDate;
    @Column(name = "\"Genre\"", nullable = false)
    @Enumerated(value = EnumType.ORDINAL)
    private Genre genre = Genre.NONE;
    @Column(name = "\"Runtime\"", nullable = false)
    private Integer runtime;
    @Column(name = "\"ImageLink\"", nullable = true, length = 1000)
    private String imageLink;
    @Column(name = "\"Series\"", nullable = false)
    private boolean series = false;
    @Column(name = "\"Episodes\"", nullable = true)
    private Integer episodes;

    public Movie() {

    }

    public Movie(Movie movie) {
        this.guid = movie.getGuid();
        this.name = movie.getName();
        this.releaseDate = movie.getReleaseDate();
        this.watchDate = movie.getWatchDate();
        this.runtime = movie.getRuntime();
        this.imageLink = movie.getImageLink();
        this.genre = movie.getGenre();
        this.series = movie.isSeries();
        this.episodes = movie.getEpisodes();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie)) return false;

        Movie that = (Movie) o;

        return guid.equals(that.guid);
    }

    @Override
    public int hashCode() {
        return guid.hashCode();
    }
}
