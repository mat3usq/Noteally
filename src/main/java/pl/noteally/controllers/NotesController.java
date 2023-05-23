package pl.noteally.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.noteally.data.Note;
import pl.noteally.services.NoteService;

import java.util.List;

@Controller
@RequestMapping("/notes")
public class NotesController {
    final private NoteService noteService;
@Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }
    public List<Note> getNote(){
        return noteService.getNote();
    }
}
