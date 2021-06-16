package dmj.movie.stats.core;

import dmj.movie.stats.common.dto.MovieDto;
import dmj.movie.stats.common.model.Movie;
import dmj.movie.stats.common.objects.DBParameters;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.flywaydb.core.Flyway;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Properties;

@Configuration
@ComponentScan(basePackages = {"dmj.movie.stats.core", "dmj.movie.stats.common.*"})
@EnableJpaRepositories(basePackages = "dmj.movie.stats.core.repository",
        transactionManagerRef = "movieTransactionManager")
public class MovieStatsCoreConfiguration {

    @Bean(name="movieDataSource")
    DataSource movieDataSource(@Qualifier("movieDBParameters") DBParameters dbParameters) {
        PGSimpleDataSource ds = new PGSimpleDataSource();
        ds.setServerName(dbParameters.getServer());
        if (dbParameters.getPort() != null)
            if (!dbParameters.getPort().equals("")) ds.setPortNumber(Integer.parseInt(dbParameters.getPort()));
        ds.setDatabaseName(dbParameters.getDatabase());
        if (!dbParameters.getSchema().equals("")) ds.setCurrentSchema(dbParameters.getSchema());
        ds.setUser(dbParameters.getUser());
        ds.setPassword(dbParameters.getPassword());

        migrate(ds);

        return ds;
    }
    private void migrate(DataSource dataSource) {
        // обновляем базу
        Flyway flyway = Flyway.configure().dataSource(dataSource).locations("classpath:moviestats").baselineOnMigrate(true).createSchemas(true).load();
        // Start the migration
        flyway.migrate();
    }

    @Bean(name="entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean movieEntityManagerFactory(@Qualifier("movieDataSource") DataSource dataSource) {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource);
        em.setPackagesToScan("dmj.movie.stats.common");

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean(name="movieTransactionManager")
    public PlatformTransactionManager movieTransactionManager(
//            @Qualifier("directoryEntityManagerFactory")
            @Qualifier("entityManagerFactory")
                    LocalContainerEntityManagerFactoryBean entityManagerFactory) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory.getObject());

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation(){
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");

        return properties;
    }
    @Bean("movieMapperFactory")
    MapperFactory mapperFactory() {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(Movie.class, MovieDto.class);

        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));

        return mapperFactory;
    }
    @Bean("movieMapperFacade")
    MapperFacade mapperFacade(@Qualifier("movieMapperFactory") MapperFactory mapperFactory) {
        return mapperFactory.getMapperFacade();
    }

}
