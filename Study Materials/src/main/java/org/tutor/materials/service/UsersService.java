package org.tutor.materials.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.tutor.materials.model.MyUserPrincipal;
import org.tutor.materials.model.dto.input.StudentInput;
import org.tutor.materials.model.dto.input.TeacherInput;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.users.Teacher;
import org.tutor.materials.repository.UsersRepository;

import java.util.List;

@Service
public class UsersService implements UserDetailsService {

    @Autowired
    UsersRepository repository;

    public Student addStudent(StudentInput input) {
        if(repository.findFirstStudentByName(input.name()) != null) {
            throw new IllegalArgumentException("Student named [" + input.name() + "] already exists.");
        }

        var newStudent = new Student();
        newStudent.setName(input.name());
        return repository.save(newStudent);
    }

    public Teacher addTeacher(TeacherInput input) {
        if(repository.findFirstTeacherByName(input.name()) != null) {
            throw new IllegalArgumentException("Teacher named [" + input.name() + "] already exists.");
        }

        var newTeacher = new Teacher();
        newTeacher.setName(input.name());
        return repository.save(newTeacher);
    }


    public List<Teacher> findAllTeachers() {
        return repository.findAllAppUsers(Teacher.class);
    }

    public List<Student> findAllStudent() {
        return repository.findAllAppUsers(Student.class);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = repository.findFirstByName(username);
        if(user == null)
            throw new UsernameNotFoundException(username);
        return new MyUserPrincipal(user);
    }

}
