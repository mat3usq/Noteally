package pl.noteally.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noteally.data.Catalog;
import pl.noteally.services.CatalogService;

import java.util.List;
@RestController
public class CatalogController {
    final private CatalogService catalogService;

    @Autowired
    public CatalogController(CatalogService catalogService) {

        this.catalogService = catalogService;
    }

    @GetMapping("catalogs")
    public String getCatalogs() {
        List<Catalog> catalogList = catalogService.getCatalogs();
        Catalog catalog = catalogList.get(0);
        return catalog.toString();
    }

}

