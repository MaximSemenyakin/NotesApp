package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.webkit.WebView;

import com.example.notesapp.R;
import com.example.notesapp.implementation.NoteRepoImpl;
import com.example.notesapp.repository.Note;
import com.example.notesapp.repository.NoteRepo;
import com.example.notesapp.repository.NotesAdapter;
import com.google.android.material.appbar.AppBarLayout;

public class MainActivity extends AppCompatActivity {

    private RecyclerView noteRecycler;
    private Toolbar notesToolbar;

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
            openEditActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    private void openEditActivity() {
        Intent intent = new Intent(this, ChangeNoteActivity.class);
        startActivity(intent);
    }

    public void initRecyclerView() {
        noteRecycler.setLayoutManager(new LinearLayoutManager(this));
        noteRecycler.setAdapter(adapter);
        adapter.updateData(noteRepo.getNotes());
        adapter.onItemClickListener(this::onItemClick);
    }

    private void onItemClick(Note item) {
        openEditActivity();
    }

    public void initViews() {
        noteRecycler = findViewById(R.id.notes_recycler);
        notesToolbar = findViewById(R.id.note_toolbar);

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