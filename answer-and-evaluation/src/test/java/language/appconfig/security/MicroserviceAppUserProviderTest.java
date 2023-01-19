package language.appconfig.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MicroserviceAppUserProviderTest {

    @Test
    public void getUserByTokenTest() {
        var service = new MicroserviceAppUserProvider();
        var userDetails = service.findByToken("token-student1");
        System.out.println("userDetails = " + userDetails);
    }

}