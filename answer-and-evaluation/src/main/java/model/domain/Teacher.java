package model.domain;

public record Teacher(
        ID<Teacher> id,
        String name
) implements AppUser, Domain {
}
