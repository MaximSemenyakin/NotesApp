package com.example.notesapp.repository;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notesapp.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private List<Note> notes = new ArrayList<>();
    private OnItemClick clickListener = null;

    public void updateData(List<Note> data) {
        this.notes = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(parent, clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public Note getItem(int position) {
        return notes.get(position);
    }


    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Note notes;

        private final TextView noteNameTv = itemView.findViewById(R.id.note_name_text_view);
        private final TextView noteDescriptionTv = itemView.findViewById(R.id.note_description_text_view);
        private final TextView noteDateTv = itemView.findViewById(R.id.create_date_text_view);

        public ViewHolder(@NonNull ViewGroup parent, NotesAdapter.OnItemClick clickListener) {
            super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
            itemView.setOnClickListener(view -> clickListener.onItemClickListener(notes));
        }

        public void bind(Note note) {
            this.notes = note;
            noteNameTv.setText(notes.getNote());
            noteDescriptionTv.setText(notes.getDescription());
        }
    }

    public void onItemClickListener(OnItemClick listener) {
        clickListener = listener;
    }

    public interface OnItemClick {
        void onItemClickListener(Note item);
    }
}
