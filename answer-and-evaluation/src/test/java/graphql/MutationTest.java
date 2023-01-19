package graphql;

import com.graphql.spring.boot.test.GraphQLTestTemplate;
import language.LanguageClassApp;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;

@SpringBootTest(classes = LanguageClassApp.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MutationTest {

    GraphQLTestTemplate template;

    @Test
    public void test() throws IOException {
        var response = template.postForResource("graphql/mutation/textbook/create-new-textbook.graphql");
        System.out.println(response);
    }

    private String getRequest(String requestName) {
        var simplePath = "graphql/" + requestName + ".graphql";
        var file = new File(getClass().getClassLoader().getResource(simplePath).getFile());
        return file.getAbsolutePath();
    }

}
