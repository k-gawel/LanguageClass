package language.graphql;

import com.coxautodev.graphql.tools.GraphQLResolver;
import language.contentandrepository.model.domain.user.Teacher;
import language.graphql.shared.DomainResolver;
import org.springframework.stereotype.Component;

@Component
public class TeacherResolver extends DomainResolver<Teacher> implements GraphQLResolver<Teacher> {

    public String id(Teacher teacher) {
        return teacher.id().id();
    }

}
