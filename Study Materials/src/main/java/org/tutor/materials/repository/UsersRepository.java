package org.tutor.materials.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.tutor.materials.model.entity.users.AppUserEntity;
import org.tutor.materials.model.entity.users.StudentEntity;
import org.tutor.materials.model.entity.users.TeacherEntity;

import java.util.List;

@Repository
public interface UsersRepository extends BasicRepository<AppUserEntity, AppUserEntity> {


    TeacherEntity findFirstTeacherByName(String name);

    StudentEntity findFirstStudentByName(String name);

    @Query("SELECT user FROM AppUserEntity user WHERE  type(user) = ?1")
    <Q extends AppUserEntity> List<Q> findAllAppUsers(Class<Q> type);

    AppUserEntity findFirstByName(String username);

}
