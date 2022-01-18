package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notesapp.R;
import com.example.notesapp.repository.Note;

public class MainActivity extends AppCompatActivity implements NoteListFragment.Controller, AddOrEditNoteFragment.Controller{

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        openListNoteFragment();
    }

    private void openListNoteFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, new NoteListFragment())
                .commit();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_note_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_and_edit_item) {
            openNoteScreen(null);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initToolbar() {
        toolbar = findViewById(R.id.note_toolbar);
        setSupportActionBar(toolbar);
    }


    @Override
    public void openNoteScreen(Note note) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, AddOrEditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void openListNoteScreen(Note note) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, NoteListFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }
}