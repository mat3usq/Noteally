package pl.noteally.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.data.User;
import pl.noteally.repositories.CatalogRepository;
import pl.noteally.repositories.NoteRepository;
import pl.noteally.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CatalogService {
    final private CatalogRepository catalogRepository;

    final private UserService userService;

    public Optional<Catalog> getCatalogById(Integer id) {
        return catalogRepository.findById(id);
    }

    public List<Catalog> getCatalogsByUserId(Integer userId) {return catalogRepository.findByUserId(userId);}

    public void saveCatalog(Catalog catalog, Integer userId)
    {
        Optional<User> user = userService.getUserById(userId);
        catalog.setUser(user.get());
        catalogRepository.save(catalog);
    }
}
