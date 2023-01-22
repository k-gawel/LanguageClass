package beanmocks;

import language.appconfig.security.AppUserDetails;
import language.appconfig.security.AppUserProvider;
import language.appconfig.security.UserAuthority;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MockedAppUserProvider implements AppUserProvider {

    @Override
    public Optional<AppUserDetails> findByToken(String token) {
        return Optional.ofNullable(switch (token) {
            case "student1" -> new AppUserDetails("student_1", UserAuthority.STUDENT);
            case "student2" -> new AppUserDetails("student_2", UserAuthority.STUDENT);
            case "teacher1" -> new AppUserDetails("teacher_1", UserAuthority.TEACHER);
            default -> null;
        });
    }

}
