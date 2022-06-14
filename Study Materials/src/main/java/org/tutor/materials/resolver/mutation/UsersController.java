package org.tutor.materials.resolver.mutation;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.dto.input.StudentInput;
import org.tutor.materials.model.dto.input.TeacherInput;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.service.UsersService;

@Component
public class UsersController implements GraphQLMutationResolver {

    @Autowired
    UsersService service;

    public Student createStudent(StudentInput input) {
        System.out.println("Create student.");
        return service.addStudent(input);
    }

    public Teacher createTeacher(TeacherInput input) {
        return service.addTeacher(input);
    }

}
