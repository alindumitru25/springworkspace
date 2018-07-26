package com.rest.async.rest;

import com.rest.async.entity.Note;
import com.rest.async.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class NoteRestController {

    @Autowired
    private NoteService noteService;

    @Autowired
    private TaskExecutor taskExecutor;

    @GetMapping("/notes")
    public List<Note> getNotes() {
        return noteService.getNotes();
    }

    @PostMapping("/notes")
    public Note saveNote(@RequestBody Note note) {
        note.setId(0);
        noteService.saveNote(note);

        return note;
    }

    @GetMapping("/notesFeed")
    public ResponseBodyEmitter notesFeed() {
        // async controller
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        taskExecutor.execute(() -> {
           List<Note> notes = noteService.getNotes();
           try {
               for (Note note : notes) {
                   emitter.send(note);
                   try {
                       // sleeps 1s before sending next, it doesnt actually send the response to the client (as Server Sent Events do)
                       // but rather waits for responses and sends multiple objects back when done
                       Thread.sleep(1000);
                   } catch (InterruptedException e) {
                        emitter.completeWithError(e);
                   }
               }
               emitter.complete();
           } catch (IOException e) {
                emitter.completeWithError(e);
           }
        });

        return emitter;
    }

    @GetMapping("/notesFeedV2")
    public SseEmitter getNotesFeedV2() {
        final SseEmitter emitter = new SseEmitter();
        taskExecutor.execute(() -> {
            List<Note> notes = noteService.getNotes();

            try {
                for (Note note : notes) {
                    // since it's using Server Sent Events the client is updated when send method is called so its basically live update through SSE
                    emitter.send(note);
                    try {
                        Thread.sleep(700);
                    } catch (InterruptedException e) {
                        emitter.completeWithError(e);
                    }
                }
                emitter.complete();
            } catch (IOException e) {
                emitter.completeWithError(e);
            }
        });

        return emitter;
    }
}
