package org.tutor.materials.textbook.service.managers.interfaces;

import org.tutor.materials.textbook.model.domain.type.ID;
import org.tutor.materials.textbook.model.domain.type.users.AppUser;
import org.tutor.materials.textbook.model.domain.type.users.Student;
import org.tutor.materials.textbook.model.domain.type.users.Teacher;

public interface UserService {

    Teacher createTeacher(ID<? extends AppUser> accessor, String name, String password);
    Student createStudent(ID<? extends AppUser> accessor, String name, String password);

}
