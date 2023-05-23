package pl.noteally.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.noteally.data.Catalog;
import pl.noteally.services.CatalogService;

import java.util.List;
@Controller
@RequestMapping("{userId}/catalogs")
public class CatalogsController {
    final private CatalogService catalogService;

    @Autowired
    public CatalogsController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("userId") Integer userId) {
        List<Catalog> catalogList = catalogService.getCatalogsByUserId(userId);
        model.addAttribute("catalogs", catalogList);

        return "catalogs";
    }
}