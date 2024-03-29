package language;

import language.appconfig.config.IgnoreDuringScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.io.IOException;
import java.time.Clock;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = {
        "language.appconfig",
        "language.graphql",
        "language.contentandrepository.persistence",
        "language.contentandrepository.repository",
        "language.service.service"
})
@EnableJpaRepositories(basePackages="language.contentandrepository.persistence.dao",
                       considerNestedRepositories = true,
                       excludeFilters = @ComponentScan.Filter(IgnoreDuringScan.class)
)
@EntityScan("language.contentandrepository.persistence.entity")
public class LanguageClassApp {

    public static void main(String[] args) throws IOException {
        runSpringApplication(args);
    }

    private static void runSpringApplication(String[] args) {
        SpringApplication app = new SpringApplication(LanguageClassApp.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8081"));
        app.run(args);
    }


    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    @Bean
    public Clock clock() {
        return  Clock.systemDefaultZone();
    }

    @Bean
    public TestRestTemplate template() {
        return new TestRestTemplate();
    }

}
