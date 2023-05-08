package com.example.store_notes.repository;

import com.example.store_notes.data.Category;
import com.example.store_notes.data.Note;
import lombok.Getter;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
@Getter
public class NoteRepository {
    LinkedList<Note> noteList = new LinkedList<>();
    LinkedList<Category> categories = new LinkedList<>();

    public NoteRepository(){
        System.out.println("Repository Of Notes Constructor");

        // TUTAJ Z BAZY BEDZIEMY DODAWAC KATEGORIE ORAZ NOTATKI

        categories.add(new Category(0,"*"));
        categories.add(new Category(1,"Wazne"));
        categories.add(new Category(2,"Malo Wazne"));

        noteList.add(new Note(0,"Spoko ok", "Fajna notakta i wgl ","Dzisiaj Stworzono", categories.get(1)));
        noteList.add(new Note(1,"Nie fjanie ", "Slaba notakta","Wczoraj Przed", categories.get(2)));
        noteList.add(new Note(2,"Nie Git ", "Slaba Ok","Nie dzis Stworzono", categories.get(2)));
        noteList.add(new Note(3,"Nie Nie Ok ", "Slaba Spadaj","Ok ok  Stworzono", categories.get(2)));
    }

    public Note getNote(int id)
    {
        return noteList.get(id);
    }

    public LinkedList<Note> getNotesFromCategory(String category){
        if(category.equals("*"))
            return noteList;

        LinkedList<Note> filteredNotes = new LinkedList<>();
        for (Note n : noteList)
            if(n.getCategory().getName().equals(category))
                filteredNotes.add(n);

        return filteredNotes;
    }
}
