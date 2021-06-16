package dmj.movie.stats.adapter.rest;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = {"dmj.movie.stats.adapter.rest"})
@PropertySource(value="file:moviestats.properties", ignoreResourceNotFound = true)
public class MovieStatsAdapterRestConfiguration {
}
