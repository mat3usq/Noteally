package pl.noteally.controllers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class Log_Reg_Controller {
    @GetMapping("/login")
    public String redirectLogin(){
        // Przekierowanie Na Login
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        // Zaloguj użytkownika

        return "redirect:/" + "1/catalogs";
    }

    @GetMapping("/registerMe")
    public String redirectRegister(){
        // Przekierowanie Na Register
        return "register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username, @RequestParam("email") String email,
                        @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword) {
        // Zarejestruj użytkownika

        // Przekierowanie na Logowanie
        return "redirect:/" + "1/catalogs";
    }
}
