package pl.noteally.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.repositories.NoteRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    private final CatalogService catalogService;
    public List<Note> getNotesByCatalogId(Integer catalogId) { return noteRepository.findByCatalogId(catalogId); }

    public void saveNote(Note note, Integer catalogId)
    {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        note.setCatalog(catalog.get());
        note.setDate(LocalDate.now());
        note.setLink(note.getTitle() + "Link");
        noteRepository.save(note);
    }

    public void deleteNoteById(Integer noteId)
    {
        noteRepository.deleteById(noteId);
    }
}
