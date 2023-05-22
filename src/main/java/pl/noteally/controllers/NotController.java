package pl.noteally.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.noteally.data.Note;
import pl.noteally.data.User;
import pl.noteally.services.NoteService;

import java.util.List;

@RestController
public class NotController {
    final private NoteService noteService;
@Autowired
    public NotController(NoteService noteService) {
        this.noteService = noteService;
    }
    public List<Note> getNote(){
        return noteService.getNote();
    }
}
