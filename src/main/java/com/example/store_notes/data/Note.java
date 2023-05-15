package com.example.store_notes.data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Note {
    private int idNote;
    private String name;
    private String content;
    private String creationDate;
    Category category;
}
