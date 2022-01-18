package com.example.notesapp.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.notesapp.R;
import com.example.notesapp.implementation.NoteRepoImpl;
import com.example.notesapp.repository.Note;
import com.example.notesapp.repository.NoteRepo;

public class AddOrEditNoteFragment extends Fragment {

    protected static final String ARGS_KEY = "args_key";
    private Controller controller;

    private EditText noteNameEt;
    private EditText noteDescriptionEt;
    private Button saveNoteBt;

    private Note note;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement AddOrEditNoteFragment.Controller");
        }
    }

    public static AddOrEditNoteFragment newInstance(Note note) {
        AddOrEditNoteFragment editNoteFragment = new AddOrEditNoteFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARGS_KEY, note);
        editNoteFragment.setArguments(bundle);
        return editNoteFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.add_edit_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViews(view);
        getArgs();
        fillViews();
        setUpListeners();
    }

    private void initViews(View view) {
        noteDescriptionEt = view.findViewById(R.id.description_note_edit_text);
        noteNameEt = view.findViewById(R.id.note_name_edit_text);
        saveNoteBt = view.findViewById(R.id.save_note_button);
    }

    private void fillViews() {
        if (note != null) {
            noteNameEt.setText(note.getNote());
            noteDescriptionEt.setText(note.getDescription());
        }
    }

    private void setUpListeners() {
        createOrEditNote();
        saveNoteBt.setOnClickListener(v -> controller.openListNoteScreen(note));
    }

    private void createOrEditNote() {
        if (note == null) {
            note = new Note(
                    noteNameEt.getText().toString(),
                    noteDescriptionEt.getText().toString()
            );
        } else {
            note.setNote(noteNameEt.getText().toString());
            note.setDescription(noteDescriptionEt.getText().toString());
        }
    }

    private void getArgs() {
        Bundle data = getArguments();
        if (data != null) {
            note = data.getParcelable(ARGS_KEY);
        }
    }


    interface Controller {
        void openListNoteScreen(Note note);
    }

    @Override
    public void onDestroy() {
        controller = null;
        super.onDestroy();
    }
}
