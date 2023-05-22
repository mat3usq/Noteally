package pl.noteally.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.noteally.data.Note;
import pl.noteally.services.NoteService;

import java.util.List;

@RestController
public class NoteController {
    final private NoteService noteService;
@Autowired
    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
    public List<Note> getNote(){
        return noteService.getNote();
    }
}
