package pl.noteally.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.data.User;
import pl.noteally.services.CatalogService;
import pl.noteally.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("{userId}/catalogs")
@AllArgsConstructor
public class CatalogsController {
    final private CatalogService catalogService;

    final private UserService userService;

    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("userId") Integer userId) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId(userId);
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/ASC")
    public String sortCatalogsASC(Model model, @PathVariable("userId") Integer userId) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId(userId);
        catalogList.sort(Comparator.comparing(Catalog::getName));
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }
    @GetMapping("/DESC")
    public String sortCatalogsDESC(Model model, @PathVariable("userId") Integer userId) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId(userId);
        catalogList.sort(Comparator.comparing(Catalog::getName).reversed());
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("catalogs", catalogList);
        model.addAttribute("user", user.get());
        return "catalogs";
    }


    @GetMapping("/createCatalog")
    public String redirectCreate(Model model, @PathVariable("userId") Integer userId){
        Catalog catalog = new Catalog();
        Optional<User> user = userService.getUserById(userId);
        model.addAttribute("catalog", catalog);
        model.addAttribute("user", user.get());
        return "createCatalog";
    }

    @PostMapping("/createCatalog")
    public String addCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult, Model model,  @PathVariable("userId") Integer userId) {
       // walidacja
        if (bindingResult.hasErrors()) {
            return "redirect:/" + userId + "/catalogs/createCatalog";
        }

        catalogService.saveCatalog(catalog, userId);
        return "redirect:/" + userId + "/catalogs";
    }


    @GetMapping("/deleteCatalog/{catalogId}")
    public String delete(Model model, @PathVariable("catalogId") Integer catalogId, @PathVariable("userId") Integer userId) {
        catalogService.deleteCatalogById(catalogId);
        return "redirect:/" + userId + "/catalogs";
    }

    @GetMapping("/editCatalog/{catalogId}")
    public String redirectEdit(Model model, @PathVariable("catalogId") Integer catalogId){
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        return "editCatalog";
    }

    @PostMapping("/editCatalog/{catalogId}")
    public String editCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, BindingResult bindingResult,  @PathVariable("catalogId") Integer catalogId,
                           @PathVariable("userId") Integer userId){

        // walidacja
        if (bindingResult.hasErrors()) {
            return "redirect:/" + userId + "/catalogs/editCatalog/{catalogId}";
        }

        catalogService.updateCatalog(catalog, catalogId);
        return "redirect:/" + userId + "/catalogs";
    }
}