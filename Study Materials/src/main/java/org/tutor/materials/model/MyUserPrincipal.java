package org.tutor.materials.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tutor.materials.model.entity.users.AppUserEntity;
import org.tutor.materials.model.entity.users.StudentEntity;

import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private final AppUserEntity user;

    public MyUserPrincipal(AppUserEntity user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authority = user.getClass().equals(StudentEntity.class) ? new StudentAuthority() : new TeacherAuthority();
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static class TeacherAuthority implements GrantedAuthority {
        @Override
        public String getAuthority() {
            return "TEACHER";
        }
    }

    public static class StudentAuthority implements GrantedAuthority {

        @Override
        public String getAuthority() {
            return "STUDENT";
        }
    }

}
