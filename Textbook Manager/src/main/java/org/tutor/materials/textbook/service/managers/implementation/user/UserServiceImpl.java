package org.tutor.materials.textbook.service.managers.implementation.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tutor.materials.textbook.model.domain.input.AppUserInput;
import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;
import org.tutor.materials.textbook.model.domain.type.users.UserRole;
import org.tutor.materials.textbook.service.events.events.UserCreationEvent;
import org.tutor.materials.textbook.service.events.processors.PublishEvent;
import org.tutor.materials.textbook.service.managers.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserCreator creator;

    @Autowired
    public UserServiceImpl(UserCreator creator) {
        this.creator = creator;
    }

    @Override
    public Teacher createTeacher(ID<? extends AppUser> accessor, String name, String password) {
        var input = new AppUserInput(name, password, UserRole.TEACHER);
        return (Teacher) createUser(accessor, input);
    }

    @Override
    public Student createStudent(ID<? extends AppUser> accessor, String name, String password) {
        var input = new AppUserInput(name, password, UserRole.STUDENT);
        return (Student) createUser(accessor, input);
    }


    @PublishEvent(UserCreationEvent.class)
    public AppUser createUser(ID<? extends AppUser> accessor, AppUserInput input) {
        return creator.createUser(input);
    }



}
