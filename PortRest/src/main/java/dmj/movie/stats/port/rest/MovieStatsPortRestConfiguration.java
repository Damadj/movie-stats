package dmj.movie.stats.port.rest;


import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dmj.movie.stats.port.rest.config.WebConfiguration;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan("dmj.movie.stats.port.rest")
@Import(WebConfiguration.class)
public class MovieStatsPortRestConfiguration {
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        return builder.modulesToInstall(new JavaTimeModule());
    }
}