package languageuser.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import languageuser.service.JwtTokenUtils;
import languageuser.service.UserService;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;
    private final UserDetailsService userDetailsService;
    private final JwtTokenUtils tokenUtils;


    @PostMapping("login")
    @CrossOrigin
    public String login(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username = " + username + ", password = " + password);
        return userService.login(username, password);
    }

    @PostMapping("registerTeacher")
    @CrossOrigin
    public String registerTeacher(@RequestParam("username") String username, @RequestParam("password") String password) {
        return userService.registerTeacher(username, password);
    }

    @PostMapping("registerStudent")
    @CrossOrigin
    public String registerStudent(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("UserController.registerStudent");
        System.out.println("username = " + username + ", password = " + password);
        var token = userService.registerStudent(username, password);
        System.out.println("token = " + token);
        return token;
    }

    @GetMapping("getUser/{token}")
    @CrossOrigin
    public UserDetails getByToken(@PathVariable String token) {
        System.out.println("UserController.getByToken");
        System.out.println("token = " + token);
        if(!tokenUtils.validate(token))
            return null;
        System.out.println("token is valid");
        var username = tokenUtils.getUsername(token);
        System.out.println("username = " + username);
        var user = userDetailsService.loadUserByUsername(username);
        return user;
    }

}
