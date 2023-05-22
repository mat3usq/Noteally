package pl.noteally.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.noteally.data.Catalog;
import pl.noteally.services.CatalogsService;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class CatalogsController {
    private final CatalogsService catalogsService;

    @GetMapping("/catalogs")
    public String listCatalogs()
    {
        return catalogsService.listCatalogs().toString();
    }
}
