package dmj.movie.stats.common.enums;

public enum Genre {
    NONE(""),
    ACTION("Action"),
    THRILLER("Thriller"),
    COMEDY("Comedy"),
    ROMANCE("Romance"),
    HORROR("Horror"),
    HISTORICAL("Historical"),
    FANTASY("Fantasy"),
    SCIENCE_FICTION("Science fiction");

    private String name;

    Genre(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
