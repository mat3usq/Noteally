package pl.noteally.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;
import pl.noteally.services.UserService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("")
    public String getAllUser(Model model) {
        List<User> userList=userService.getUsers();
        model.addAttribute("userList", userList);
        return "admin";
    }

    //Zarządzanie rolami
}
