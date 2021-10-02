package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.notesapp.R;
import com.example.notesapp.implementation.NoteRepoImpl;
import com.example.notesapp.repository.Note;
import com.example.notesapp.repository.NoteRepo;
import com.example.notesapp.repository.NotesAdapter;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteRecycler;
    private Toolbar notesToolbar;

    private final int REQUEST_CODE_UPDATE = 1;
    private final int REQUEST_CODE_NEW = 2;

    private TextView noteNameTv;
    private TextView descriptionNoteTv;

    NotesAdapter adapter = new NotesAdapter();
    NoteRepo noteRepo = new NoteRepoImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        fillValues();

        initRecyclerView();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_and_edit_item) {
            createNewNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openEditActivity(@NonNull Note item) {
        Intent intent = new Intent(this, ChangeNoteActivity.class);
            intent.putExtra(ChangeNoteActivity.NAME_NOTE, item.getNote());
            intent.putExtra(ChangeNoteActivity.DESCRIPTION_NOTE, item.getDescription());
            startActivityForResult(intent, REQUEST_CODE_UPDATE);
            startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_CODE_UPDATE) {
                assert data != null;
                noteNameTv.setText(data.getStringExtra("name"));
                descriptionNoteTv.setText(data.getStringExtra("description"));
            }
            else {
                Toast.makeText(this, "Wrong result", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void initRecyclerView() {
        noteRecycler.setLayoutManager(new LinearLayoutManager(this));
        noteRecycler.setAdapter(adapter);
        adapter.updateData(noteRepo.getNotes());
        adapter.onItemClickListener(this::onItemClick);
    }

    private void onItemClick(Note item) {
        openEditActivity(item);
    }

    private void createNewNote() {
        Intent intent = new Intent(this, ChangeNoteActivity.class);
        startActivity(intent);
    }

    public void initViews() {
        noteRecycler = findViewById(R.id.notes_recycler);
        notesToolbar = findViewById(R.id.note_toolbar);
        noteNameTv = findViewById(R.id.note_name_text_view);
        descriptionNoteTv = findViewById(R.id.note_description_text_view);

        setSupportActionBar(notesToolbar);
    }

    public void fillValues() {
        noteRepo.createNote(new Note(1, "Note first", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(2, "Note second", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(3, "Note third", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(4, "Note four", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note five", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note six", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note seven", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note eight", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note nine", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note ten", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note eleven", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
        noteRepo.createNote(new Note(5, "Note twelve", "How to create a note app using java and android " +
                "with recycler view, and many other thing", "01.10.2021"));
    }
}