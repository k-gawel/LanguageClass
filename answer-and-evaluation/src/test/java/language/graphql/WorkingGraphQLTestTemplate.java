package language.graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ResourceLoader;

public class WorkingGraphQLTestTemplate extends GraphQLTestTemplate {

    private final int port;
    private String host = "http://localhost";
    private String endpoint = "/graphql";

    public WorkingGraphQLTestTemplate(int port, ResourceLoader resourceLoader, TestRestTemplate testRestTemplate) {
        this.port = port;
        setUrl();
        setResourceLoader(resourceLoader);
        setRestTemplatw(testRestTemplate);
    }

    private String getUrl() {
        return host + ":" + port + endpoint;
    }

    private void setRestTemplatw(TestRestTemplate value) {
        try {
            var field = GraphQLTestTemplate.class.getDeclaredField("restTemplate");
            field.setAccessible(true);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    private void setResourceLoader(ResourceLoader value) {
        try {
            var field = GraphQLTestTemplate.class.getDeclaredField("resourceLoader");
            field.setAccessible(true);
            field.set(this, value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public void setUrl() {
        try {
            var field = GraphQLTestTemplate.class.getDeclaredField("graphqlMapping");
            field.setAccessible(true);
            field.set(this, getUrl());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

}
