package pl.noteally.controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.noteally.data.Catalog;
import pl.noteally.data.User;
import pl.noteally.services.CatalogService;
import pl.noteally.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/catalogs")
@AllArgsConstructor
public class CatalogsController {
    final private CatalogService catalogService;

    final private UserService userService;

    @GetMapping("")
    public String getCatalogsByUserId(Model model, HttpSession session, HttpServletRequest request) {
        Cookie cookie = (Cookie) request.getAttribute("catalogCookie");
        if (cookie.getName().equals("ASC"))
            return "redirect:/catalogs/ASC";
        else if (cookie.getName().equals("DESC"))
            return "redirect:/catalogs/ASC";
        else if (cookie.getName().equals("notesASC"))
            return "redirect:/catalogs/notesASC";
        else if (cookie.getName().equals("notesDESC"))
            return "redirect:/catalogs/notesDESC";

        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/ASC")
    public String sortCatalogsASC(Model model, HttpSession session, HttpServletRequest request) {
        request.setAttribute("catalogCookie", new Cookie("catalogCookie", "ASC"));
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getName));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/DESC")
    public String sortCatalogsDESC(Model model, HttpSession session, HttpServletRequest request) {
        request.setAttribute("catalogCookie", new Cookie("catalogCookie", "DESC"));
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getName).reversed());
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }

    @GetMapping("/notesASC")
    public String sortCatalogsByNotesASC(Model model, HttpSession session, HttpServletRequest request) {
        request.setAttribute("catalogCookie", new Cookie("catalogCookie", "notesASC"));
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getNoteCount));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }

    @GetMapping("/notesDESC")
    public String sortCatalogsByNotesDESC(Model model, HttpSession session, HttpServletRequest request) {
        request.setAttribute("catalogCookie", new Cookie("catalogCookie", "notesDESC"));
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getNoteCount).reversed());
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }

    @GetMapping("/createCatalog")
    public String redirectCreate(Model model, HttpSession session){
        Catalog catalog = new Catalog();
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalog", catalog);
        model.addAttribute("user", user.get());
        return "createCatalog";
    }

    @PostMapping("/createCatalog")
    public String addCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult, Model model, HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult);
            return "createCatalog";
        }
        catalogService.saveCatalog(catalog, (Integer) session.getAttribute("userId"));
        return "redirect:/catalogs";
    }

    @GetMapping("/deleteCatalog/{catalogId}")
    public String delete(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Integer userId = (Integer)session.getAttribute("userId");
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        if(catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("default")) && !(catalog.get().getName().equals("shared")))
        {
            catalogService.deleteCatalogById(catalogId);
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/editCatalog/{catalogId}")
    public String redirectEdit(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session){
        Integer userId = (Integer)session.getAttribute("userId");
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        if(catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("default")) && !(catalog.get().getName().equals("shared")))
        {
            model.addAttribute("catalog", catalog.get());
            return "editCatalog";
        }
        return "redirect:/catalogs";
    }

    @PostMapping("/editCatalog/{catalogId}")
    public String editCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult,  @PathVariable("catalogId") Integer catalogId){

        if (bindingResult.hasErrors()) {
            return "redirect:/catalogs/editCatalog/{catalogId}";
        }
        catalogService.updateCatalog(catalog, catalogId);
        return "redirect:/catalogs";
    }

    @PostMapping("/search")
    public String search(@RequestParam(name="search") String search, HttpSession session, Model model){

        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList.stream().filter(c -> c.getName().contains(search)).toList());
        model.addAttribute("user", user.get());
        return "catalogs";
    }
}