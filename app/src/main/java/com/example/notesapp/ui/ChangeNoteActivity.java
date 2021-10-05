package com.example.notesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.R;
import com.example.notesapp.repository.Note;

public class ChangeNoteActivity extends AppCompatActivity {

    private EditText noteNameEt;
    private EditText descriptionEt;
    private Button saveButton;

    private Note note;

    protected static final String UPDATE_MAIN = "update_main";
    protected static final String UPDATE = "update";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_note);

        initViews();
        fillViews();

        saveButton.setOnClickListener(v -> saveNoteButton());
    }

    private void initViews() {
        noteNameEt = findViewById(R.id.note_name_edit_text);
        descriptionEt = findViewById(R.id.description_edit_text);
        saveButton = findViewById(R.id.save_note_button);
    }

    private void fillViews() {
        note = getIntent().getParcelableExtra(UPDATE);

        if (note != null) {
            noteNameEt.setText(note.getNote());
            descriptionEt.setText(note.getDescription());
        }
    }

    private void updateNote(Note note) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(UPDATE_MAIN, note);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void saveNoteButton() {
        String nameNote = noteNameEt.getText().toString();
        String descriptionNote = descriptionEt.getText().toString();

        if (nameNote != null && descriptionNote != null) {
            Note item = new Note(nameNote, descriptionNote);
            updateNote(item);
        }
    }
}
