package com.rest.async.dao;

import com.rest.async.entity.Note;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteDAOImpl implements NoteDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Note> getNotes() {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<Note> getAllQuery = currentSession.createQuery("from Note ", Note.class);

        return getAllQuery.getResultList();
    }

    @Override
    public void saveNote(Note note) {
        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.saveOrUpdate(note);
    }

}
