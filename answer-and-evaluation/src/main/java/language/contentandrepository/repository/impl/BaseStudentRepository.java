package language.contentandrepository.repository.impl;

import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.mapper.user.StudentEntityMapper;
import language.contentandrepository.repository.user.StudentRepository;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.entity.user.StudentEntity;

@Repository("studentRepository")
public class BaseStudentRepository extends BaseContentRepository<Student, StudentEntity>
                                   implements StudentRepository {

    public BaseStudentRepository(StudentEntityMapper entityToModelMapper,
                                 StudentJpa jpaRepository,
                                 StudentJpa.ID idsJpaRepository) {
        super(Student.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
