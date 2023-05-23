package pl.noteally.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noteally.data.User;
import pl.noteally.services.UserService;

import java.util.List;
@RestController
public class UserController {
    final private UserService userService;

@Autowired
    public UserController(UserService userService) {
    this.userService = userService;
    }

    @GetMapping("users")
    public List<User> getUsers() {
        return userService.getUsers();
    }

}

