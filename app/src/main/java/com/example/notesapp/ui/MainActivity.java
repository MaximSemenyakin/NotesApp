package com.example.notesapp.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.notesapp.R;
import com.example.notesapp.repository.Note;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity implements
        NoteListFragment.Controller,
        AddOrEditNoteFragment.Controller {

    private Toolbar toolbar;
    private BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToolbar();
        initNavigation();

        navigationView.setSelectedItemId(R.id.list_item);
    }

    private void initNavigation() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.settings_item:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new SettingsFragment())
                            .commit();
                    break;
                case R.id.profile_item:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new ProfileFragment())
                            .commit();
                    break;
                case R.id.list_item:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.fragment_container, new NoteListFragment())
                            .commit();
                    break;
            }
            return true;
        });
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