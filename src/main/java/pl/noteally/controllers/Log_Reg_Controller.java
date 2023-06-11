package pl.noteally.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.noteally.data.User;
import pl.noteally.services.UserService;

import java.lang.reflect.Field;

@Controller
@AllArgsConstructor
public class Log_Reg_Controller {

    private final UserService userService;
    @GetMapping("/login")
    public String redirectLogin(){
        // Przekierowanie Na Login
        return "login";
    }

    @GetMapping("/registerMe")
    public String redirectRegister(Model model){
        User user= new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam ("confirmPassword") String confirmPassword) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        if(!user.getPassword().equals(confirmPassword))
        {
            return "register";
        }
        userService.signUpUser(user);
        return "login";
    }
}
