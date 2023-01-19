package language.rest;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestTest {

    @GetMapping("/test")
    @PreAuthorize("hasAuthority('TEACHER')")
    public String test() {
        return "test";
    }

    @GetMapping("/testUsername")
    public String testUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
