package dmj.movie.stats.sb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.stereotype.Component;

@Component
@SpringBootApplication
public class ServerApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }
}
