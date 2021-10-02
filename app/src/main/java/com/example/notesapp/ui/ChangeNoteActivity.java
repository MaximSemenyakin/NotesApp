package com.example.notesapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.notesapp.R;

public class ChangeNoteActivity extends AppCompatActivity {

    private EditText noteNameEt;
    private EditText descriptionEt;
    private Button saveButton;

    protected static final String NAME_NOTE = "name_note";
    protected static final String DESCRIPTION_NOTE = "description_note";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_note);

        initViews();
        fillViews();

        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent();
            String name = noteNameEt.getText().toString();
            String description = descriptionEt.getText().toString();
            intent.putExtra("name", name);
            intent.putExtra("description", description);
            setResult(RESULT_OK, intent);
            finish();
        });
    }

    private void initViews() {
        noteNameEt = findViewById(R.id.note_name_edit_text);
        descriptionEt = findViewById(R.id.description_edit_text);
        saveButton = findViewById(R.id.save_note_button);
    }

    private void fillViews() {
        Intent intent = getIntent();
        String nameNote = intent.getStringExtra(NAME_NOTE);
        String descriptionNote = intent.getStringExtra(DESCRIPTION_NOTE);

        noteNameEt.setText(nameNote);
        descriptionEt.setText(descriptionNote);
    }
}
