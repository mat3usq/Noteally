package pl.noteally.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Log_Reg_Controller {
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // Zaloguj użytkownika

        return "main";
    }

    @GetMapping("/registerMe")
    public String redirect(){
        // Przekierowanie Na Register
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("email") String email,
                        @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        // Zarejestruj użytkownika

        // Przekierowanie na Logowanie
        return "index";
    }
}
