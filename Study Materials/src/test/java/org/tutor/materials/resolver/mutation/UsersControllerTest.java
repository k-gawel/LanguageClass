package org.tutor.materials.resolver.mutation;

import org.tutor.materials.model.dto.input.TeacherInput;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.GraphQlTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@GraphQlTest
class UsersControllerTest {

    @Autowired
    UsersController controller;

    @MockBean
    UsersService service;

    @Test
    public void createTeacherWithUniqueName() {
        var input = new TeacherInput("UniqueName", "PASSWORD1234");

        var teacher = new Teacher();
        teacher.setId(1L);
        teacher.setName(input.name());

        doReturn(teacher).when(service).addTeacher(input);
        var output = controller.createTeacher(input);

    }


}