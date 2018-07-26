package com.rest.async.service;

import com.rest.async.dao.NoteDAO;
import com.rest.async.entity.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class NoteServiceImpl implements NoteService{

    @Autowired
    private NoteDAO noteDAO;

    @Override
    @Transactional
    public List<Note> getNotes() {
        return noteDAO.getNotes();
    }

    @Override
    @Transactional
    public void saveNote(Note note) {
        noteDAO.saveNote(note);
    }

}
