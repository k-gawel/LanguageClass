package language.appconfig.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


@Component
public class PostgresDataSource extends DriverManagerDataSource {

    public PostgresDataSource() {
        setDriverClassName("org.postgresql.Driver");
        setUrl("jdbc:postgresql://localhost:5432/language_study_material");
        setUsername("postgres");
        setPassword("password");
    }

}
