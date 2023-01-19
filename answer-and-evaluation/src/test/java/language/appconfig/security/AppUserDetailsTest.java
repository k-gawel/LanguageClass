package language.appconfig.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserDetailsTest {

    @Test
    public void serializationTest() throws IOException {
        var details = new AppUserDetails("username", UserAuthority.TEACHER);
        var jackson = new ObjectMapper();
        var serialized = jackson.writeValueAsString(details);
        assertEquals(details,
                jackson.readValue(serialized.getBytes(StandardCharsets.UTF_8), AppUserDetails.class));
    }

}