package com.example.notesapp.repository;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notes = new ArrayList<>();

    public void updateData(List<Note> data) {
        this.notes = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note note = getItem(position);
        holder.noteNameTv.setText(note.getNote());
        holder.noteDateTv.setText(note.getDate());
        holder.noteDescriptionTv.setText(note.getDescription());
    }

    public Note getItem(int position) {
        return notes.get(position);
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView noteNameTv, noteDescriptionTv, noteDateTv;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteNameTv = itemView.findViewById(R.id.note_name_text_view);
            noteDescriptionTv = itemView.findViewById(R.id.note_description_text_view);
            noteDateTv = itemView.findViewById(R.id.create_date_text_view);
        }
    }
}
