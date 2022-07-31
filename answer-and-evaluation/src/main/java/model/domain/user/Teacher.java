package model.domain.user;

import model.domain.Domain;
import model.domain.ID;

public record Teacher(
        ID<Teacher> id,
        String name
) implements AppUser, Domain {
}
