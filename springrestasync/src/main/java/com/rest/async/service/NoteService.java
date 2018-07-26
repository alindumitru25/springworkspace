package com.rest.async.service;

import com.rest.async.entity.Note;

import java.util.List;

public interface NoteService {

    public List<Note> getNotes();

    public void saveNote(Note note);

}
