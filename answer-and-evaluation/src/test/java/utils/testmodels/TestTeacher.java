package utils.testmodels;

import language.contentandrepository.model.domain.user.Teacher;

public class TestTeacher {

    public static Teacher generate() {
        return new Teacher(
                TestModel.generateId(Teacher.class),
                "name"
        );
    }

}
