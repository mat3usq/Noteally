package pl.noteally.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.services.CatalogService;
import pl.noteally.services.NoteService;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("{userId}/catalogs/{catalogId}")
@AllArgsConstructor
public class NotesController {
    final private NoteService noteService;
    final private CatalogService catalogService;
    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        noteList.sort(Comparator.comparing(Note::getDate).reversed());
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        model.addAttribute("notes", noteList);
        return "notes";
    }
    @GetMapping("/ASC")
    public String sortNotesByTitleASC(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        noteList.sort(Comparator.comparing(Note::getTitle).reversed());
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        model.addAttribute("notes", noteList);
        return "notes";
    }
    @GetMapping("/DESC")
    public String sortNotesByTitleDESC(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        noteList.sort(Comparator.comparing(Note::getTitle).reversed());
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        model.addAttribute("notes", noteList);
        return "notes";
    }
    @GetMapping("/dataASC")
    public String sortNotesByDateASC(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        noteList.sort(Comparator.comparing(Note::getDate));
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        model.addAttribute("notes", noteList);
        return "notes";
    }
    @GetMapping("/dataDESC")
    public String sortNotesByDateDESC(Model model, @PathVariable("catalogId") Integer catalogId) {
        List<Note> noteList = noteService.getNotesByCatalogId(catalogId);
        noteList.sort(Comparator.comparing(Note::getDate).reversed());
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("catalog", catalog.get());
        model.addAttribute("notes", noteList);
        return "notes";
    }


    @GetMapping("/createNote")
    public String redirectCreate(Model model, @PathVariable("catalogId") Integer catalogId){
        // Przekierowanie Na CreateNote
        Note note = new Note();
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        model.addAttribute("note", note);
        model.addAttribute("catalog", catalog.get());
        return "createNote";
    }

    @PostMapping("/createNote")
    public String addNote(Model model, @Valid @ModelAttribute("note") Note note, @PathVariable("catalogId") Integer catalogId,
                          @PathVariable("userId") Integer userId) {

        noteService.saveNote(note, catalogId);
        return "redirect:/" + userId + "/catalogs/" + catalogId;
    }

    @GetMapping("/deleteNote/{noteId}")
    public String delete(Model model, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId,
                         @PathVariable("userId") Integer userId){
        noteService.deleteNoteById(noteId);
        return "redirect:/" + userId + "/catalogs/" + catalogId;
    }

    @GetMapping("/editNote/{noteId}")
    public String redirectEdit(Model model, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId,
                         @PathVariable("userId") Integer userId){
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Optional<Note> note = noteService.getNoteById(noteId);
        model.addAttribute("note", note.get());
        model.addAttribute("catalog", catalog.get());
        return "editNote";
    }

    @PostMapping("/editNote/{noteId}")
    public String editNote(@PathVariable("noteId") Integer noteId,  @Valid @ModelAttribute("note") Note note, @PathVariable("catalogId") Integer catalogId,
                       @PathVariable("userId") Integer userId){
        noteService.updateNote(note, noteId);
        return "redirect:/" + userId + "/catalogs/" + catalogId;
    }
}
