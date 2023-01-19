package languageuser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = { "languageuser.controller", "languageuser.service" })
@EnableJpaRepositories(basePackages = "languageuser.service.model")
public class UserModuleApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UserModuleApp.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8082"));
        app.run(args);
    }

    @Bean
    public DataSource dataSource() {
        var dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/language_platform_users");
        dataSource.setUsername("postgres");
        dataSource.setPassword("password");
        return dataSource;
    }

}
