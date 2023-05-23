package pl.noteally.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.services.NoteService;

import java.util.List;

@Controller
@RequestMapping("{userId}/catalogs/{catalogId}")
public class NotesController {
    final private NoteService noteService;
    @Autowired
    public NotesController(NoteService noteService) {
        this.noteService = noteService;
    }
    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        model.addAttribute("notes", noteList);
        return "notes";
    }

    @GetMapping("/createNote")
    public String redirect(){
        // Przekierowanie Na CreateNote
        return "createNote";
    }
}
