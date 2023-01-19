package utils.testmodels;

import language.contentandrepository.model.domain.user.Student;

public class TestStudent  {

    public static Student generate() {
        return new Student(
                TestModel.generateId(Student.class),
                "name"
        );
    }

}
