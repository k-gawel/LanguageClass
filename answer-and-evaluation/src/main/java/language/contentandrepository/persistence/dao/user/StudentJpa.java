package language.contentandrepository.persistence.dao.user;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.user.StudentEntity;

import java.util.List;
import java.util.Optional;

public interface StudentJpa extends IdentifiableEntityJpaRepository<StudentEntity> {

    @Override
    default Optional<StudentEntity> findEntityById(String id) {
        return findStudentEntityById(id);
    }

    Optional<StudentEntity> findStudentEntityById(String id);

    interface ID extends IdsJpaRepository<StudentEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM StudentEntity WHERE id LIKE '#1%%'")
        List<String> findIdsStartingWith(String beginString);

    }

}
