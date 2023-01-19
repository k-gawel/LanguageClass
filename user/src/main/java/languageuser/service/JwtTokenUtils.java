package languageuser.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import languageuser.service.model.AppUserEntity;

@Service
@RequiredArgsConstructor
public class JwtTokenUtils {

    public boolean validate(String token) {
        return token.matches(".*-.*");
    }

    public String getUsername(String token) {
        return token.split("-")[1];
    }

    public String generateAccessToken(AppUserEntity user) {
        return "token-" + user.getUsername();
    }

}
