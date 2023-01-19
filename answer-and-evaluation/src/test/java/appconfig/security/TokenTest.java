package appconfig.security;

import language.LanguageClassTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureMockRestServiceServer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@AutoConfigureMockRestServiceServer
@SpringBootTest(classes = LanguageClassTest.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TokenTest {

    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void test() throws Exception {
        assertEquals(HttpStatus.OK, sendRequest("token-student1", "test").getStatusCode());
        assertNotEquals(HttpStatus.OK, sendRequest("randomToken", "test").getStatusCode());
        assertEquals("student1", sendRequest("token-student1", "testUsername").getBody());
    }

    private ResponseEntity<String> sendRequest(String token, String endpoint) {
        var host = "http://localhost:" + port + "/";
        var headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        var entity = new HttpEntity<>(headers);
        return template.exchange(host + endpoint, HttpMethod.GET, entity, String.class);
    }


}
