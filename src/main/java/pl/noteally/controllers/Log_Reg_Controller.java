package pl.noteally.controllers;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.noteally.data.User;

@Controller
@AllArgsConstructor
public class Log_Reg_Controller {
    @GetMapping("/login")
    public String redirectLogin(){
        // Przekierowanie Na Login
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "login";
        }
        //logowanie
        return "redirect:/" + "1/catalogs";
    }

    @GetMapping("/registerMe")
    public String redirectRegister(Model model){
        User user= new User();
        model.addAttribute("user",user);
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        //zarejestruj
        return "redirect:/" + "1/catalogs";
    }
}
