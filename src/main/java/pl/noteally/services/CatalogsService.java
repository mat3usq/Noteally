package pl.noteally.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.noteally.data.Catalog;
import pl.noteally.repositories.CatalogRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CatalogsService {

    private final CatalogRepository catalogRepository;
    public List<Catalog> listCatalogs()
    {
        return catalogRepository.findAll();
    }
}
