package language.contentandrepository.repository.user;

import language.contentandrepository.model.domain.user.Student;
import language.contentandrepository.repository.BaseContentRepository;
import language.contentandrepository.repository.DomainCache;
import language.contentandrepository.persistence.dao.user.StudentJpa;
import language.contentandrepository.persistence.mapper.user.StudentEntityMapper;
import org.springframework.stereotype.Repository;
import language.contentandrepository.persistence.entity.user.StudentEntity;

@Repository
public class StudentRepository extends BaseContentRepository<Student, StudentEntity> {

    public StudentRepository(StudentEntityMapper entityToModelMapper,
                             StudentJpa jpaRepository,
                             StudentJpa.ID idsJpaRepository) {
        super(Student.class, entityToModelMapper, new DomainCache<>(), jpaRepository, idsJpaRepository);
    }

}
