package dmj.movie.stats.common.objects;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBParameters {
    private String server;
    private String port;
    private String database;
    private String user;
    private String password;
    private String schema;

    public DBParameters() {
    }
}