package language.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.contentandrepository.model.domain.user.Student;
import language.graphql.shared.DomainResolver;
import org.springframework.stereotype.Component;

@Component
public class StudentResolver extends DomainResolver<Student> implements GraphQLResolver<Student> {

    public String id(Student Student) {
        return Student.id().id();
    }

}

