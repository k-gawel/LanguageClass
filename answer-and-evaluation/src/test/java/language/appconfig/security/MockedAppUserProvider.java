package language.appconfig.security;

import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MockedAppUserProvider implements AppUserProvider {

    @Override
    public Optional<AppUserDetails> findByToken(String token) {
        return Optional.ofNullable(switch (token) {
            case "student_1" -> new AppUserDetails("student_1", UserAuthority.STUDENT);
            case "student_2" -> new AppUserDetails("student_2", UserAuthority.STUDENT);
            case "teacher_1" -> new AppUserDetails("teacher_1", UserAuthority.TEACHER);
            default -> null;
        });
    }

}
