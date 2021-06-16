package dmj.movie.stats.common.objects;

public class MovieStatsException extends Exception {

    private static final long serialVersionUID = 569288542086788792L;

    public MovieStatsException() {
    }

    public MovieStatsException(String arg0) {
        super(arg0);
    }

    public MovieStatsException(Throwable arg0) {
        super(arg0);
    }

    public MovieStatsException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }

    public String toString() {
        String exception = super.toString();
        if (this.getCause() != null) {
            exception = exception + "(" + this.getCause().toString() + ")";
        }

        return exception;
    }

    public MovieStatsException initCause(Throwable e) {
        super.initCause(e);
        return this;
    }
}
