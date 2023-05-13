package com.example.store_notes.controllers;

import com.example.store_notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/notes")
public class NoteController {
    @Autowired
    NoteRepository noteRepository;

    @GetMapping("/")
    public String getNotes(Model model){
        model.addAttribute("notes", noteRepository.getNoteList());
        model.addAttribute("categories", noteRepository.getCategories());
        return "notes";
    }

    @GetMapping("/{id}")
    public String getNote(@PathVariable int id, Model model){
        model.addAttribute("note", noteRepository.getNote(id));
        return "note";
    }

    @GetMapping("/category")
    public String getNote(@RequestParam String category, Model model){
        model.addAttribute("notes", noteRepository.getNotesFromCategory(category));
        model.addAttribute("categories", noteRepository.getCategories());
        return "notes";
    }
}