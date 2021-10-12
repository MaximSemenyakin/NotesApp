package com.example.notesapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;
import com.example.notesapp.implementation.NoteRepoImpl;
import com.example.notesapp.repository.Note;
import com.example.notesapp.repository.NoteRepo;
import com.example.notesapp.repository.NotesAdapter;

public class NoteListFragment extends Fragment {

    private RecyclerView noteRecycler;
    private NotesAdapter notesAdapter = new NotesAdapter();

    private Note note;
    private NoteRepo noteRepo = new NoteRepoImpl();

    private static final String TAG = "@@@";

    private Controller controller;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement NoteListFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.note_list_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fillValues();
        getArgs();
        initRecycler(view);

    }

    private void initRecycler(View view) {
        noteRecycler = view.findViewById(R.id.note_list_recycler);
        noteRecycler.setLayoutManager(new LinearLayoutManager(getContext()));
        noteRecycler.setAdapter(notesAdapter);
        notesAdapter.updateData(noteRepo.getNotes());
        notesAdapter.onItemClickListener(this::onItemClick);
    }

    private void onItemClick(Note note) {
        controller.openNoteScreen(note);
    }

    public static NoteListFragment newInstance(Note note) {
        NoteListFragment listFragment = new NoteListFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(AddOrEditNoteFragment.ARGS_KEY, note);
        listFragment.setArguments(bundle);
        Log.d(TAG, "newInstance() called with: note = [" + note + "]");
        return listFragment;
    }

    private void getArgs() {
        Bundle data = getArguments();
        if (data != null) {
            note = data.getParcelable(AddOrEditNoteFragment.ARGS_KEY);
            if (note != null) {
                if (note.getId() == null)
                    noteRepo.createNote(note);
                    else {
                    noteRepo.updateNote(note.getId(), note);
                }
            }
        }
        notesAdapter.updateData(noteRepo.getNotes());
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

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }

    interface Controller {
        void openNoteScreen(Note note);
    }
}
