package model.domain.user;

import model.domain.Domain;
import model.domain.ID;

public interface AppUser extends Domain {
    ID<? extends AppUser> id();
    String name();
}
