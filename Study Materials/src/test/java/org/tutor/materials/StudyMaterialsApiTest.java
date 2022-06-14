package org.tutor.materials;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StudyMaterialsApiTest {


    @Test
    public void test() {
        StudyMaterialsApi.main(new String[] {});
    }

}
