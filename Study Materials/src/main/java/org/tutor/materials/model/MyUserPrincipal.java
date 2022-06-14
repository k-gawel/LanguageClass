package org.tutor.materials.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.tutor.materials.model.entity.users.AppUser;
import org.tutor.materials.model.entity.users.Student;

import java.util.Collection;
import java.util.List;

public class MyUserPrincipal implements UserDetails {

    private final AppUser user;

    public MyUserPrincipal(AppUser user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        var authority = user.getClass().equals(Student.class) ? new StudentAuthority() : new TeacherAuthority();
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
