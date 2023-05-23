package pl.noteally.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.noteally.data.Note;
import pl.noteally.repositories.NoteRepository;

import java.util.List;
@Service
public class NoteService {
    final private NoteRepository noteRepository;
@Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }
    public List<Note> getNotes(){
        return noteRepository.findAll();
    }

    public List<Note> getNotesByCatalogId(Integer catalogId) { return noteRepository.findByCatalogId(catalogId); }
}
