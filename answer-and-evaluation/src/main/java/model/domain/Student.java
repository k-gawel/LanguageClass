package model.domain;

public record Student(
        ID<Student> id,
        String name
) implements Domain, AppUser {
}
