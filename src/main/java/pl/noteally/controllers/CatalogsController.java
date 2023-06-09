package pl.noteally.controllers;


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
    public String getCatalogsByUserId(Model model, HttpSession session) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/ASC")
    public String sortCatalogsASC(Model model, HttpSession session) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getName));
        Optional<User> user = userService.getUserById((Integer) session.getAttribute("userId"));
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/DESC")
    public String sortCatalogsDESC(Model model, HttpSession session) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId((Integer) session.getAttribute("userId"));
        catalogList.sort(Comparator.comparing(Catalog::getName).reversed());
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
    public String addCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult, Model model,  HttpSession session) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors",bindingResult);
            return "redirect:/catalogs/createCatalog";
        }

        catalogService.saveCatalog(catalog, (Integer) session.getAttribute("userId"));
        return "redirect:/catalogs";
    }

    @GetMapping("/deleteCatalog/{catalogId}")
    public String delete(Model model, @PathVariable("catalogId") Integer catalogId) {
        catalogService.deleteCatalogById(catalogId);
        return "redirect:/catalogs";
    }

    @GetMapping("/editCatalog/{catalogId}")
    public String redirectEdit(Model model, @PathVariable("catalogId") Integer catalogId){
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        return "editCatalog";
    }

    @PostMapping("/editCatalog/{catalogId}")
    public String editCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult,  @PathVariable("catalogId") Integer catalogId){

        if (bindingResult.hasErrors()) {
            return "redirect:/catalogs/editCatalog/{catalogId}";
        }
        catalogService.updateCatalog(catalog, catalogId);
        return "redirect:/catalogs";
    }
}