package dmj.movie.stats.port.rest.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

public class DispatcherServletInitXml extends AbstractDispatcherServletInitializer {
    @Override
    protected WebApplicationContext createServletApplicationContext() { return null; }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/init/*"};
    }

    @Override
    protected WebApplicationContext createRootApplicationContext() {
        return null;
    }
}
