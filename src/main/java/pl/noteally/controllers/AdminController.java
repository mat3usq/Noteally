package pl.noteally.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.noteally.data.Catalog;
import pl.noteally.data.User;
import pl.noteally.repositories.UserRepository;
import pl.noteally.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    @GetMapping("")
    public String getAllUser(Model model, HttpSession session) {
        List<User> userList=userService.getUsers();
        model.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/ASC")
    public String sortCatalogsASC(Model model) {
        List<User> userList=userService.getUsers();
        userList.sort(Comparator.comparing(User::getName));
        model.addAttribute("userList", userList);
        return "admin";
    }
    @GetMapping("/DESC")
    public String sortCatalogsDESC(Model model) {
        List<User> userList=userService.getUsers();
        userList.sort(Comparator.comparing(User::getName).reversed());
        model.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/deleteUser/{userId}")
    public String delete(Model model, @PathVariable("userId") Integer userId) {
        userService.deleteUserById(userId);
        return "redirect:/admin";
    }
    @GetMapping("/editUser/{userId}")
    public String redirectEdit(Model model, @PathVariable("userId") Integer userId){
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("user", user.get());
        return "editUser";
    }

    @PostMapping("/editUser/{userId}")
    public String editUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,  @PathVariable("userId") Integer userId){
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/admin/editUser/{userId}";
        }
        userService.updateUser(user, userId);
        return "redirect:/admin";
    }
}