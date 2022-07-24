package model.domain;

public interface AppUser extends Domain {
    ID<? extends AppUser> id();
    String name();
}
