package model.domain.user;

import model.domain.Domain;
import model.domain.ID;

public record Student(
        ID<Student> id,
        String name
) implements Domain, AppUser {
}
