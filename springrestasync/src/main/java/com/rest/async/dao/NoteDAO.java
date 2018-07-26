package com.rest.async.dao;

import java.util.List;

import com.rest.async.entity.Note;

public interface NoteDAO {

    public List<Note> getNotes();

    public void saveNote(Note note);

}
