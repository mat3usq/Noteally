package pl.noteally.controllers;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
    public String redirectLogin(HttpSession session){
        if(session.getAttribute("userId") != null)
        {
            return "redirect:/catalogs";
        }
        return "login";
    }

    @GetMapping("/register")
    public String redirectRegister(Model model, HttpSession session){
        if(session.getAttribute("userId") != null)
        {
            return "redirect:/catalogs";
        }

        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, @RequestParam ("confirmPassword") String confirmPassword, HttpServletResponse response, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("name", user.getName());
        model.addAttribute("surname", user.getSurname());
        model.addAttribute("age", user.getAge());
        model.addAttribute("password", user.getPassword());

        System.out.println(user.toString());
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult);
            return "register";
        }
        if(!user.getPassword().equals(confirmPassword))
        {
            model.addAttribute("confirm", "Passwords are diffrent");
            model.addAttribute("errors",bindingResult);
            return "register";
        }
        userService.signUpUser(user);

        Cookie catalogCookie = new Cookie("catalogCookie" + user.getId(), "default");
        catalogCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        Cookie noteCookie = new Cookie("noteCookie" + user.getId(), "default");
        noteCookie.setMaxAge(60 * 60 * 24 * 365 * 10);
        response.addCookie(catalogCookie);
        response.addCookie(noteCookie);

        return "login";
    }
}
