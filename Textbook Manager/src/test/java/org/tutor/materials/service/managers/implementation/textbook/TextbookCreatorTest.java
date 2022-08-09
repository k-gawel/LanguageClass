package org.tutor.materials.service.managers.implementation.textbook;

import org.junit.jupiter.api.Test;
import org.tutor.materials.textbook.exceptions.NotFoundException;
import org.tutor.materials.textbook.model.domain.input.TextbookInput;
import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.model.entity.shared.AbstractEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.service.managers.implementation.textbook.TextbookCreator;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextbookCreatorTest {

    private final DAO dao = new DAO() {
        @Override
        public <Q extends AbstractEntity> void save(Q entity) {
        }

        @Override
        public <Q extends AbstractEntity> List<Q> findByIds(Collection ids, Class<Q> entity) {
            Constructor<Q> constructor;
            try {
                constructor = entity.getConstructor();
            } catch (NoSuchMethodException e) {
                throw new IllegalStateException();
            }
            return getIds(ids).stream().map(i -> {
                try {
                    Q e = constructor.newInstance();
                    e.setId(i);
                    return e;
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException ex) {
                    ex.printStackTrace();
                    throw new IllegalStateException();
                }
            }).toList();
        }

        @Override
        public <Q extends AbstractEntity> Q getReference(ID<? extends Domain> id, Class<Q> entityClass) {
            if(id.id().startsWith("nonExistent"))
                throw new NotFoundException(id);
            if(entityClass.equals(StudentEntity.class))
                return (Q) new StudentEntity() {
                    @Override
                    public String getId() {
                        return id.id();
                    }
                };
            else if(entityClass.equals(TeacherEntity.class))
                return (Q) new TeacherEntity() {
                    @Override
                    public String getId() {
                        return id.id();
                    }
                };
            else
                throw new IllegalStateException();
        }
    };

    private final TextbookCreator textbookCreator = new TextbookCreator(dao);

    @Test
    public void givenTextbookInputWithProperAllowedStudents_whenFromInput_thenReturnEntity() {
        var input = new TextbookInput(
                "title", new ID<>(Teacher.class, "teacher_id"), true, Collections.emptyList()
        );

        var entity = textbookCreator.fromInput(input);

        assertEquals(input.title(), entity.getTitle());
        assertEquals(input.isPublic(), entity.isPublic());
//        assertEquals(0, entity.getContentIds().size());
        assertEquals(input.creator().id(), entity.getCreatedBy().getId());
    }

}