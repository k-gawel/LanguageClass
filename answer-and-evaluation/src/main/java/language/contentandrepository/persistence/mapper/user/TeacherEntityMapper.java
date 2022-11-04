package language.contentandrepository.persistence.mapper.user;


import language.contentandrepository.persistence.mapper.EntityToModelMapper;
import lombok.AllArgsConstructor;
import language.contentandrepository.model.DomainID;
import language.contentandrepository.model.domain.user.Teacher;
import org.springframework.stereotype.Component;
import language.contentandrepository.persistence.entity.user.TeacherEntity;

@Component
@AllArgsConstructor
public class TeacherEntityMapper implements EntityToModelMapper<TeacherEntity, Teacher> {

    private final language.contentandrepository.persistence.dao.user.TeacherJpa TeacherJpa;

    @Override
    public Teacher fromEntity(TeacherEntity entity) {
        return new Teacher(
                new DomainID<>(Teacher.class, entity.getId()),
                entity.getName()
        );
    }

    @Override
    public TeacherEntity toEntity(Teacher model)  {
        throw new IllegalCallerException("No user.graphqls persistence in this service.");
    }

}

