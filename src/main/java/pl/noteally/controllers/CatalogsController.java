package pl.noteally.controllers;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.noteally.data.Catalog;
import pl.noteally.data.User;
import pl.noteally.services.CatalogService;
import pl.noteally.services.UserService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("{userId}/catalogs")
@AllArgsConstructor
public class CatalogsController {
    final private CatalogService catalogService;

    final private UserService userService;

    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("userId") Integer userId) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId(userId);
        catalogList.sort(Comparator.comparing(Catalog::getName));
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
    public String addCatalog(Model model, @Valid @ModelAttribute("catalog") Catalog catalog, @PathVariable("userId") Integer userId) {

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
    public String editCatalog(@Valid @ModelAttribute("catalog") Catalog catalog, @PathVariable("catalogId") Integer catalogId,
                           @PathVariable("userId") Integer userId){
        catalogService.updateCatalog(catalog, catalogId);
        return "redirect:/" + userId + "/catalogs";
    }
}