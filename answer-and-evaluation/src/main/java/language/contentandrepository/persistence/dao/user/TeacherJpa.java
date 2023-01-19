package language.contentandrepository.persistence.dao.user;

import language.contentandrepository.persistence.dao.IdsJpaRepository;
import org.springframework.data.jpa.repository.Query;
import language.contentandrepository.persistence.dao.IdentifiableEntityJpaRepository;
import language.contentandrepository.persistence.entity.user.TeacherEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherJpa extends IdentifiableEntityJpaRepository<TeacherEntity> {

    @Override
    default Optional<TeacherEntity> findEntityById(String id) {
        return findTeacherEntityById(id);
    }

    Optional<TeacherEntity> findTeacherEntityById(String id);

    interface ID extends IdsJpaRepository<TeacherEntity.ID> {

        @Override
        @Query("SELECT new java.lang.String(id) FROM TeacherEntity WHERE id LIKE concat(?1, '__%%')")
        List<String> findIdsStartingWith(String beginString);


    }
    
}
