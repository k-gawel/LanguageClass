package language.graphql.shared;

import com.coxautodev.graphql.tools.GraphQLResolver;
import graphql.schema.DataFetchingEnvironment;
import language.contentandrepository.model.TestType;
import language.contentandrepository.model.TestTypeChild;
import org.springframework.stereotype.Component;

@Component
public class TestTypeResolver implements GraphQLResolver<TestType> {

    public String id(TestType type, DataFetchingEnvironment environment) {
        System.out.println("Resolving id");
        System.out.println(environment.getArguments());
        System.out.println(environment.getFields());

        return "String id";
    }

    public TestTypeChild child(TestType type, DataFetchingEnvironment environment) {
        System.out.println("Resolving child");

        System.out.println();

        environment.getFields().stream()
                        .forEach(System.out::println);
//
//        environment.getFields().stream()
//                        .map(Field::getSelectionSet)
//                        .map(SelectionSet::getSelections)
//                .flatMap(s -> s.stream().map(i -> (Field) i).map(Field::getName))
//                .forEach(System.out::println);

        return new TestTypeChild("id", "name");
    }


}
