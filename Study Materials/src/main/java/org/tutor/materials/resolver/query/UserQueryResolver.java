package org.tutor.materials.resolver.query;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.service.UsersService;

import java.util.List;

@Component
public class UserQueryResolver implements GraphQLQueryResolver {

    @Autowired
    private UsersService service;

    public List<Teacher> findAllTeachers() {
        return service.findAllTeachers();
    }

    public List<Student> findAllStudents() {
        return service.findAllStudent();
    }

}
