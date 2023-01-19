package language.contentandrepository.persistence.mapper.user;

import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Student;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.entity.user.StudentEntity;

@Component
@AllArgsConstructor
public class StudentEntityMapper implements EntityToModelMapper<StudentEntity, Student> {

    private final StudentJpa studentJpa;

    @Override
    public Student fromEntity(StudentEntity entity) {
        return new Student(
                new DomainID<>(Student.class, entity.getId()),
                entity.getName()
        );
    }

    @Override
    public StudentEntity toEntity(Student model)  {
        throw new IllegalCallerException("No user.graphqls persistence in this languageuser.service.");
    }

}
