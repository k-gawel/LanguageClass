package language.appconfig.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MicroserviceAppUserProvider implements AppUserProvider {

    private final String userServiceHost = "http://localhost:8082/";


    @Override
    public Optional<AppUserDetails> findByToken(String token) {
        var url = userServiceHost + "getUser/" + token;
        System.out.println("url = " + url);
        RestTemplate restTemplate = new RestTemplate();
        var details = restTemplate.getForObject(url, AppUserDetails.class);
        return Optional.ofNullable(details);
    }


}