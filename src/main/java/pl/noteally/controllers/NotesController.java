package pl.noteally.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    public String redirect(Model model){
        // Przekierowanie Na CreateNote
        Note note = new Note();
        model.addAttribute("note", note);
        return "createNote";
    }

    @PostMapping("/createNote")
    public String addNote(Model model, @Valid @ModelAttribute("note") Note note, @PathVariable("catalogId") Integer catalogId) {

        noteService.saveNote(note, catalogId);
        return "redirect:/";
    }
}
