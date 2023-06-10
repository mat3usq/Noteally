package pl.noteally.controllers;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.noteally.data.Catalog;
import pl.noteally.data.Note;
import pl.noteally.data.SharedNote;
import pl.noteally.data.User;
import pl.noteally.services.CatalogService;
import pl.noteally.services.NoteService;
import pl.noteally.services.UserService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/catalogs/{catalogId}")
@AllArgsConstructor
public class NotesController {
    final private NoteService noteService;
    final private CatalogService catalogService;
    final private UserService userService;

    @GetMapping("")
    public String getCatalogsByUserId(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");

        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId)) {
            List<Note> noteList;
            if (catalog.get().getName().equals("shared")) {
                noteList = noteService.getNotesFromSharedByCatalogId(userId);
            } else {
                noteList = noteService.getNotesByCatalogId(catalogId);
            }
            model.addAttribute("sharedNotes", noteService.getMySharedNotes(userId));
            model.addAttribute("catalog", catalog.get());
            model.addAttribute("notes", noteList);
            return "notes";
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/ASC")
    public String sortNotesByTitleASC(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {

        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId)) {
            List<Note> noteList;
            if (catalog.get().getName().equals("shared")) {
                noteList = noteService.getNotesFromSharedByCatalogId(userId);
            } else {
                noteList = noteService.getNotesByCatalogId(catalogId);
            }
            model.addAttribute("sharedNotes", noteService.getMySharedNotes(userId));
            noteList.sort(Comparator.comparing(Note::getTitle));
            model.addAttribute("catalog", catalog.get());
            model.addAttribute("notes", noteList);
            return "notes";
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/DESC")
    public String sortNotesByTitleDESC(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId)) {
            List<Note> noteList;
            if (catalog.get().getName().equals("shared")) {
                noteList = noteService.getNotesFromSharedByCatalogId(userId);
            } else {
                noteList = noteService.getNotesByCatalogId(catalogId);
            }
            model.addAttribute("sharedNotes", noteService.getMySharedNotes(userId));
            noteList.sort(Comparator.comparing(Note::getTitle).reversed());
            model.addAttribute("catalog", catalog.get());
            model.addAttribute("notes", noteList);
            return "notes";
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/dataASC")
    public String sortNotesByDateASC(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId)) {
            List<Note> noteList;
            if (catalog.get().getName().equals("shared")) {
                noteList = noteService.getNotesFromSharedByCatalogId(userId);
            } else {
                noteList = noteService.getNotesByCatalogId(catalogId);
            }
            model.addAttribute("sharedNotes", noteService.getMySharedNotes(userId));
            noteList.sort(Comparator.comparing(Note::getDate));
            model.addAttribute("catalog", catalog.get());
            model.addAttribute("notes", noteList);
            return "notes";
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/dataDESC")
    public String sortNotesByDateDESC(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId)) {
            List<Note> noteList;
            if (catalog.get().getName().equals("shared")) {
                noteList = noteService.getNotesFromSharedByCatalogId(userId);
            } else {
                noteList = noteService.getNotesByCatalogId(catalogId);
            }
            model.addAttribute("sharedNotes", noteService.getMySharedNotes(userId));
            noteList.sort(Comparator.comparing(Note::getDate).reversed());
            model.addAttribute("catalog", catalog.get());
            model.addAttribute("notes", noteList);
            return "notes";
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/createNote")
    public String redirectCreate(Model model, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("shared"))) {
            Note note = new Note();
            model.addAttribute("note", note);
            model.addAttribute("catalog", catalog.get());
            return "createNote";
        }
        return "redirect:/catalogs";
    }

    @PostMapping("/createNote")
    public String addNote(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult, Model model, @PathVariable("catalogId") Integer catalogId) {
        // walidacja
        if (bindingResult.hasErrors()) {
            return "redirect:/catalogs/" + catalogId + "/createNote";
        }

        noteService.saveNote(note, catalogId);
        return "redirect:/catalogs/" + catalogId;
    }

    @GetMapping("/deleteNote/{noteId}")
    public String delete(Model model, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("shared"))) {
            noteService.deleteNoteById(noteId);
            return "redirect:/catalogs/" + catalogId;
        }
        return "redirect:/catalogs";
    }

    @GetMapping("/editNote/{noteId}")
    public String redirectEdit(Model model, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("shared"))) {
            Optional<Note> note = noteService.getNoteById(noteId);
            model.addAttribute("note", note.get());
            model.addAttribute("catalog", catalog.get());
            return "editNote";
        }
        return "redirect:/catalogs";
    }

    @PostMapping("/editNote/{noteId}")
    public String editNote(@Valid @ModelAttribute("note") Note note, BindingResult bindingResult, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId) {
        // walidacja
        if (bindingResult.hasErrors()) {
            return "redirect:/catalogs/" + catalogId + "/editNote/" + noteId;
        }

        noteService.updateNote(note, noteId);
        return "redirect:/catalogs/" + catalogId;
    }

    @GetMapping("/shareNote/{noteId}")
    public String redirectShare(Model model, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && !(catalog.get().getName().equals("shared"))) {
            List<User> userList = userService.getUsers();
            Optional<Note> note = noteService.getNoteById(noteId);
            model.addAttribute("users", userList);
            model.addAttribute("note", note.get());
            model.addAttribute("catalog", catalog.get());
            return "shareNote";
        }
        return "redirect:/catalogs";
    }

    @PostMapping("/shareNote/{noteId}")
    public String shareNote(@RequestParam(value = "username", required = true) String username, @PathVariable("noteId") Integer noteId, @PathVariable("catalogId") Integer catalogId, Model model) {
        Optional<User> user = userService.getUserByUsername(username);
        if (user.isPresent()) {
            SharedNote sharedNote = new SharedNote();
            sharedNote.setNote(noteService.getNoteById(noteId).get());
            sharedNote.setUser(user.get());
            noteService.saveSharedNote(sharedNote);
            return "redirect:/catalogs/" + catalogId;
        }
        return "redirect:/catalogs/";
    }

    @GetMapping("/deleteSharedNote/{shareId}")
    public String deleteSharedNote(Model model, @PathVariable("shareId") Integer shareId, @PathVariable("catalogId") Integer catalogId, HttpSession session) {
        Optional<Catalog> catalog = catalogService.getCatalogById(catalogId);
        Integer userId = (Integer) session.getAttribute("userId");
        Optional<SharedNote> share = noteService.getShareById(shareId);
        if (catalog.isPresent() && catalog.get().getUser().getId().equals(userId) && share.isPresent() && share.get().getNote().getCatalog().getUser().getId().equals(userId) && !(catalog.get().getName().equals("shared"))) {

            noteService.deleteShareById(shareId);
            return "redirect:/catalogs/" + catalogId;
        }
        return "redirect:/catalogs";
    }
}