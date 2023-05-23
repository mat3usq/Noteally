package pl.noteally.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.repositories.CatalogRepository;
import pl.noteally.repositories.NoteRepository;

import java.util.List;

@Service
public class CatalogService {
    final private CatalogRepository catalogRepository;
    @Autowired
    public CatalogService(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
    }
    public List<Catalog> getCatalogs() {
        return catalogRepository.findAll();
    }

    public List<Catalog> getCatalogsByUserId(Integer userId) {return catalogRepository.findByUserId(userId);}
}
