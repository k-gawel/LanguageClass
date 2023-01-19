package language.graphql;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.stereotype.Component;

@Component
public class TestQueryResolver implements GraphQLQueryResolver {

    public String getTest() {
        return "test";
    }
    public String testQuery() { return "testQuery"; }

}
