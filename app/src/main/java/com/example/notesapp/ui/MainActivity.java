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

    private Note note;

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

    private void openEditActivity(Note item) {
        Intent intent = new Intent(this, ChangeNoteActivity.class);
        intent.putExtra(ChangeNoteActivity.UPDATE, item);
        startActivityForResult(intent, REQUEST_CODE_UPDATE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            note = data.getParcelableExtra(ChangeNoteActivity.UPDATE_MAIN);
            if (requestCode == REQUEST_CODE_UPDATE) {
                noteRepo.updateNote(note.getId(), note);
            } else if (requestCode == REQUEST_CODE_NEW) {
                noteRepo.createNote(note);
            }
        }
        initRecyclerView();
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
        startActivityForResult(intent, REQUEST_CODE_NEW);
    }

    public void initViews() {
        noteRecycler = findViewById(R.id.notes_recycler);
        notesToolbar = findViewById(R.id.note_toolbar);
        noteNameTv = findViewById(R.id.note_name_text_view);
        descriptionNoteTv = findViewById(R.id.note_description_text_view);

        setSupportActionBar(notesToolbar);
    }

    public void fillValues() {
        noteRepo.createNote(new Note("Note first", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note second", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note third", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note four", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note five", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note six", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note seven", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note eight", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note nine", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note ten", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note eleven", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
        noteRepo.createNote(new Note("Note twelve", "How to create a note app using java and android " +
                "with recycler view, and many other thing"));
    }
}