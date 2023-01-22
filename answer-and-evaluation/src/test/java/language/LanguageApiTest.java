package language;

import language.appconfig.security.MockedAppUserProvider;
import language.contentandrepository.repository.impl.MockedAppUserRepository;
import language.contentandrepository.repository.impl.MockedStudentRepository;
import language.contentandrepository.repository.impl.MockedTeacherRepository;
import language.appconfig.config.IgnoreDuringScan;
import language.appconfig.security.MicroserviceAppUserProvider;
import language.contentandrepository.repository.impl.BaseAppUserRepository;
import language.contentandrepository.repository.impl.BaseStudentRepository;
import language.contentandrepository.repository.impl.BaseTeacherRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
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
@ComponentScan(
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                        MockedAppUserRepository.class,
                        MockedTeacherRepository.class,
                        MockedStudentRepository.class,
                        MockedAppUserProvider.class
                })
        },
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, value = {
                LanguageClassApp.class,
                LanguageClassTest.class,
                MicroserviceAppUserProvider.class,
                BaseAppUserRepository.class,
                BaseTeacherRepository.class,
                BaseStudentRepository.class
        })
})
@EnableJpaRepositories(basePackages="language.contentandrepository.persistence.dao",
        considerNestedRepositories = true,
        excludeFilters = @ComponentScan.Filter(IgnoreDuringScan.class)
)
@EntityScan("language.contentandrepository.persistence.entity")
public class LanguageApiTest {

    public static void main(String[] args) throws IOException {
        runSpringApplication(args);
    }

    private static void runSpringApplication(String[] args) {
        SpringApplication app = new SpringApplication(LanguageApiTest.class);
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
    public TestRestTemplate testRestTemplate() {
        return new TestRestTemplate();
    }

//    @Bean
//    public GraphQLSchema schema() {
//        System.out.println("language.LanguageApiTest.schema");
//        var schemaFIle = getClass().getClassLoader().getResource("graphql/schema.graphqls");
//        System.out.println("schemaFIle = " + schemaFIle);
//        return null;
//    }




}
