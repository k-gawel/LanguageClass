package language;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.time.Clock;
import java.util.Collections;

@SpringBootApplication(scanBasePackages = { "language.rest", "language.appconfig.security" },
        exclude={DataSourceAutoConfiguration.class})
public class LanguageClassTest {

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
    public Clock clock() {
        return  Clock.systemDefaultZone();
    }

    @Bean
    public TestRestTemplate template() {
        return new TestRestTemplate();
    }

}
