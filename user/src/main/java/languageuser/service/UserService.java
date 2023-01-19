package languageuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import languageuser.service.model.AppUserEntity;
import languageuser.service.model.AppUserEntityRepository;

@Component
@RequiredArgsConstructor
public class UserService {

    private final AppUserEntityRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtils tokenUtils;

    public String login(String userName, String password) {
        var user = repository.findAppUserEntityByUsername(userName)
                .filter(u -> passwordEncoder.matches(password, u.getPassword()))
                .orElseThrow(() -> new BadCredentialsException(""));
        return tokenUtils.generateAccessToken(user);
    }

    public String registerTeacher(String username, String password) {
        repository.findAppUserEntityByUsername(username).ifPresent(a -> {
            System.out.println("Username taken " + username);
            throw new IllegalArgumentException("Username taken");
        });

        var newTeacher = new AppUserEntity();
        newTeacher.setUsername(username);
        newTeacher.setPassword(passwordEncoder.encode(password));
        newTeacher.setRole("TEACHER");
        repository.save(newTeacher);

        return tokenUtils.generateAccessToken(newTeacher);
    }

    public String registerStudent(String username, String password) {
        repository.findAppUserEntityByUsername(username).ifPresent(a -> {
            System.out.println("Username taken " + username);
            throw new IllegalArgumentException("Username taken");
        });

        var newTeacher = new AppUserEntity();
        newTeacher.setUsername(username);
        newTeacher.setPassword(passwordEncoder.encode(password));
        newTeacher.setRole("TEACHER");
        repository.save(newTeacher);

        return tokenUtils.generateAccessToken(newTeacher);
    }


}
