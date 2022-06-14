package org.tutor.materials.service;

import org.tutor.materials.model.dto.input.StudentInput;
import org.tutor.materials.model.dto.input.TeacherInput;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.repository.UsersRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UsersServiceTest {

    private final String existingStudentName = "ExistingStudent";
    private final String existingTeacherName = "ExistingTeacher";

    private final String uniqueStudentName = "UniqueStudentName";
    private final String uniqueTeacherName = "UniqueTeacherName";

    private final String PASSWORD = "PASSWORD1234";

    @Autowired
    UsersService service;
    @Autowired
    UsersRepository repository;

    @BeforeEach
    void addUsers() {
        var firstStudent = new Student();
        firstStudent.setName(existingStudentName);

        var firstTeacher = new Teacher();
        firstTeacher.setName(existingTeacherName);

        repository.save(firstStudent);
        repository.save(firstTeacher);
    }

    @Test
    public void addTeacherWithNameAlreadyExisting() {
        var studentInput = new StudentInput(existingStudentName, PASSWORD);
        var teacherInput = new TeacherInput(existingTeacherName, PASSWORD);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addStudent(studentInput);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            service.addTeacher(teacherInput);
        });
    }

    @Test
    public void addUniqueUser() {
        var studentInput = new StudentInput(uniqueStudentName, PASSWORD);
        var student = service.addStudent(studentInput);

        var teacherInput = new TeacherInput(uniqueTeacherName, PASSWORD);
        var teacher = service.addTeacher(teacherInput);

        assertNotNull(student.getId());
        Assertions.assertEquals(studentInput.name(), student.getName());

        assertNotNull(teacher.getId());
        Assertions.assertEquals(teacherInput.name(), teacher.getName());
    }

}