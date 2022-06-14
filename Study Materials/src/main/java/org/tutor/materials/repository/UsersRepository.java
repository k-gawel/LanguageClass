package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.users.AppUser;
import org.tutor.materials.model.entity.users.Student;
import org.tutor.materials.model.entity.users.Teacher;

import java.util.List;

@Repository
public interface UsersRepository extends BasicRepository<AppUser> {


    Teacher findFirstTeacherByName(String name);

    Student findFirstStudentByName(String name);

    @Query(value = "SELECT user FROM AppUser user WHERE  type(user) = ?1")
    <Q extends AppUser> List<Q> findAllAppUsers(Class<Q> type);

    AppUser findFirstByName(String username);

}
