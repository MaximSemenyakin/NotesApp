package com.example.notesapp.repository;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Nullable;

public class Note implements Parcelable {

    @Nullable
    private Integer id;
    private String note;
    private String description;
    private String date;

    public Note(String note, String description) {
        this.note = note;
        this.description = description;
    }

    protected Note(Parcel in) {
        id = in.readInt();
        note = in.readString();
        description = in.readString();
        date = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

    @Nullable
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(note);
        dest.writeString(description);
        dest.writeString(date);
    }
}
