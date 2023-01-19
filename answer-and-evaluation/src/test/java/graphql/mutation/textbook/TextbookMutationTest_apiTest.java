package graphql.mutation.textbook;

import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import graphql.mutation.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

@ExtendWith(SpringExtension.class)
@GraphQLTest
public class TextbookMutationTest_apiTest {

    private final String root = Utils.mutationRoot + "textbook/";

    @Autowired
    private GraphQLTestTemplate template;

    @Test
    @WithMockUser(authorities = "TEACHER", username = "teacher__1")
    public void createTextbookTest() throws IOException {
        final String mutation = "graphql/mutation/textbook/createTextbookTest.graphql";
        System.out.println(template.postForResource(mutation).getRawResponse());
    }

}
