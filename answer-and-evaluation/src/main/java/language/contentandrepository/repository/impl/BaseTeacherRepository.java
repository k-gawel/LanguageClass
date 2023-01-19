package language.contentandrepository.repository.impl;

import language.contentandrepository.model.domain.user.Teacher;
import language.contentandrepository.persistence.dao.user.TeacherJpa;
import language.contentandrepository.persistence.entity.user.TeacherEntity;
import language.contentandrepository.persistence.mapper.user.TeacherEntityMapper;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.repository.user.TeacherRepository;
import org.springframework.stereotype.Repository;

@Repository("teacherRepository")
public class BaseTeacherRepository extends BaseContentRepository<Teacher, TeacherEntity>
                                   implements TeacherRepository {

    public BaseTeacherRepository(TeacherEntityMapper entityToModelMapper,
                                 TeacherJpa jpaRepository,
                                 TeacherJpa.ID idsJpaRepository) {
        super(Teacher.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
