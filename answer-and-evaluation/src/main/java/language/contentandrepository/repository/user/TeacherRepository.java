package language.contentandrepository.repository.user;

import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.dao.user.TeacherJpa;
import language.contentandrepository.persistence.entity.user.TeacherEntity;
import language.contentandrepository.persistence.mapper.user.TeacherEntityMapper;

@Repository
public class TeacherRepository extends BaseContentRepository<Teacher, TeacherEntity> {

    public TeacherRepository(TeacherEntityMapper entityToModelMapper,
                             TeacherJpa jpaRepository,
                             TeacherJpa.ID idsJpaRepository) {
        super(Teacher.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
