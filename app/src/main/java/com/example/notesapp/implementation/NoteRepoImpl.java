package com.example.notesapp.implementation;

import com.example.notesapp.repository.Note;
import com.example.notesapp.repository.NoteRepo;

import java.util.ArrayList;
import java.util.List;

public class NoteRepoImpl implements NoteRepo {

    ArrayList<Note> notes = new ArrayList<>();
    private int counter = 0;

    @Override
    public List<Note> getNotes() {
        return new ArrayList<>(notes);
    }

    @Override
    public boolean createNote(Note note) {
        note.setId(++counter);
        notes.add(note);
        return true;
    }

    @Override
    public boolean updateNote(int id, Note note) {
        deleteNote(id);
        note.setId(id);
        notes.add(note);
        return true;
    }

    @Override
    public boolean deleteNote(int id) {
        for (int i = 0; i < notes.size(); i++) {
            if (notes.get(i).getId() == id) {
                notes.remove(i);
                return true;
            }
        }
        return false;
    }
}
