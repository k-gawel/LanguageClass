package org.tutor.materials.textbook.service.managers.implementation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.AppUserInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.model.entity.users.AppUserEntity;
import org.tutor.materials.textbook.model.entity.users.StudentEntity;
import org.tutor.materials.textbook.model.entity.users.TeacherEntity;
import org.tutor.materials.textbook.service.DAO;
import org.tutor.materials.textbook.service.managers.implementation.Creator;

@Service
public class UserCreator extends Creator {

    private final DAO dao;

    @Autowired
    public UserCreator(DAO dao) {
        super(dao);
        this.dao = dao;
    }

    AppUser createUser(AppUserInput input) {
        return switch (input.role()) {
            case TEACHER -> createTeacher(input.name(), input.password());
            case STUDENT -> createStudent(input.name(), input.password());
        };
    }

    Teacher createTeacher(String name, String password) {
        var entity = createTeacherEntity(name, password);
        var teacher = new Teacher(new ID<>(Teacher.class, entity.getId()), entity.getName());
        return teacher;
    }

    Student createStudent(String name, String password) {
        var entity = createStudentEntity(name, password);
        var student = new Student(new ID<>(Student.class, entity.getId()), entity.getName());
        return student;
    }

    private TeacherEntity createTeacherEntity(String name, String password) {
        var entity = new TeacherEntity();
        return createEntity(name, password, entity);
    }

    private StudentEntity createStudentEntity(String name, String password) {
        var entity = new StudentEntity();
        return createEntity(name, password, entity);
    }

    private <Q extends AppUserEntity> Q createEntity(String name, String password, Q entity) {
        entity.setName(name);
        entity.setPassword(password);
        saveNewEntity(entity);
        return entity;
    }

}
