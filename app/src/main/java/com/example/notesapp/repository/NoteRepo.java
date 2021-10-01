package com.example.notesapp.repository;

import java.util.List;

//CRUD - create read update delete

public interface NoteRepo {

    List<Note> getNotes();

    boolean createNote(Note note);

    boolean updateNote(int id, Note note);

    boolean deleteNote(int id);
}
