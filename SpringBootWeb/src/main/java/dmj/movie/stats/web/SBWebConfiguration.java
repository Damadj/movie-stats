package dmj.movie.stats.web;

import com.google.common.collect.ImmutableMap;
import dmj.movie.stats.adapter.rest.MovieStatsAdapterRestConfiguration;
import org.springframework.beans.factory.config.CustomScopeConfigurer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.faces.webapp.FacesServlet;

@Configuration
@ComponentScan(basePackages = {"dmj.movie.stats.common", "dmj.movie.stats.web", "dmj.movie.stats.adapter.rest"})
@Import({MovieStatsAdapterRestConfiguration.class})
@PropertySource(value="file:moviestats.properties", ignoreResourceNotFound = true)
public class SBWebConfiguration implements WebMvcConfigurer {

    @Bean
    public static CustomScopeConfigurer viewScope() {
        CustomScopeConfigurer configurer = new CustomScopeConfigurer();
        configurer.setScopes(new ImmutableMap.Builder<String, Object>().put("view", new ViewScope()).build());
        return configurer;
    }
//
//    @Bean
//    public InternalResourceViewResolver xhtmlViewResolver() {
//        InternalResourceViewResolver resolver= new InternalResourceViewResolver();
//        resolver.setPrefix("/");
//        resolver.setSuffix(".xhtml");
//        return resolver;
//    }
//
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController("/")
//                .setViewName("forward:/ms.xhtml");
//        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
//    }

    @Bean
    public ServletRegistrationBean<FacesServlet> servletRegistrationBean() {
        ServletRegistrationBean<FacesServlet> servletRegistrationBean = new ServletRegistrationBean<>(
                new FacesServlet(), "*.xhtml");
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {

        return sc -> {
            sc.addListener(com.sun.faces.config.ConfigureListener.class);
            sc.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
            sc.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
            sc.setInitParameter("primefaces.THEME", "glass-x");
//            sc.setInitParameter("primefaces.THEME", "black-tie");
//            sc.setInitParameter("com.sun.faces.expressionFactory", "com.sun.el.ExpressionFactoryImpl");
//            sc.setInitParameter("facelets.DEVELOPMENT", Boolean.TRUE.toString());
//            sc.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
//            sc.setInitParameter("javax.faces.FACELETS_LIBRARIES", "springsecurity.taglib.xml");
//            sc.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "1");
//            sc.setInitParameter("javax.faces.PARTIAL_STATE_SAVING_METHOD", Boolean.TRUE.toString());
//            sc.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
//            sc.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
//            sc.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", Boolean.TRUE.toString());
//            sc.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());

        };
    }
}
