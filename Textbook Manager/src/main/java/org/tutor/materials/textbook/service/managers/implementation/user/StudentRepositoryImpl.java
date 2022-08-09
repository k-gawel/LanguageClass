package org.tutor.materials.textbook.service.managers.implementation.user;

import org.springframework.stereotype.Repository;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.content.Textbook;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.service.repository.interfaces.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    private final String SQL_NEW_STUDENT =
            "SELECT new org.tutor.materials.model.domain.type.users.Student(e.id, e.name) ";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Student> findById(ID<Student> id) {
        var sql = SQL_NEW_STUDENT + "FROM StudentEntity e WHERE e.id = :id";
        try {
            var result = entityManager.createQuery(sql, Student.class)
                    .setParameter("id", id.id())
                    .getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Student> findByIds(Collection<ID<Student>> studentIds) {
        var ids = studentIds.stream().map(ID::id).toList();
        var sql = SQL_NEW_STUDENT + "FROM StudentEntity e WHERE e.id IN :ids";
        return entityManager.createQuery(sql, Student.class)
                .setParameter("ids", ids)
                .getResultList();
    }

    @Override
    public List<Student> getTextbookAccessors(ID<Textbook> textbookId) {
        var sql = SQL_NEW_STUDENT + "FROM TextbookEntity t JOIN t.users e WHERE t.id = :textbookId";
        return entityManager.createQuery(sql, Student.class)
                .getResultList();
    }
}
