package languageuser.service.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.Optional;

public enum UserAuthority implements GrantedAuthority {
    TEACHER("TEACHER"), STUDENT("STUDENT");

    private final String stringValue;

    UserAuthority(String string) {
        this.stringValue = string;
    }

    @Override
    public String getAuthority() {
        return stringValue;
    }

    public static Optional<UserAuthority> fromStringValue(String stringValue) {
        return Arrays.stream(values()).filter(a -> a.stringValue.equals(stringValue)).findFirst();
    }

}
