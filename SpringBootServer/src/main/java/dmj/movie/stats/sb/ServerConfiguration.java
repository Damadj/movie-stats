package dmj.movie.stats.sb;

import dmj.movie.stats.common.objects.DBParameters;
import dmj.movie.stats.core.MovieStatsCoreConfiguration;
import dmj.movie.stats.port.rest.MovieStatsPortRestConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan(basePackages = {"dmj.movie.stats.sb"})
@Import({MovieStatsCoreConfiguration.class, MovieStatsPortRestConfiguration.class})
@PropertySource(value = "file:moviestats.properties", ignoreResourceNotFound = true)
public class ServerConfiguration {
    @Value("${db_server:localhost}")
    private String db_server;
    @Value("${db_port:5432}")
    private String db_port;
    @Value("${db_database:moviestats}")
    private String db_database;
    @Value("${db_login:postgres}")
    private String db_login;
    @Value("${db_password:30420403}")
    private String db_password;
    @Value("${db_schema:}")
    private String db_schema;

    @Bean(name="movieDBParameters")
    DBParameters movieDBParameters() {
        DBParameters ds = new DBParameters();
        ds.setServer(db_server);
        ds.setPort(db_port);
        ds.setDatabase(db_database);
        ds.setUser(db_login);
        ds.setPassword(db_password);
        ds.setSchema(db_schema);
        return ds;
    }
}
