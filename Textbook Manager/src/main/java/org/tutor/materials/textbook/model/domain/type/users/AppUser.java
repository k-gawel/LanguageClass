package org.tutor.materials.textbook.model.domain.type.users;

import org.tutor.materials.textbook.model.domain.type.Domain;
import org.tutor.materials.textbook.model.domain.type.ID;

public interface AppUser extends Domain {

    ID<? extends AppUser> id();
    String name();
    UserRole role();

}
