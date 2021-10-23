package com.example.webflux.controllers;

import com.example.webflux.models.Note;
import com.example.webflux.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping()
    public Mono<?> addNote(@RequestBody Note note){
      log.info("Add note : "+note.toString());
      noteService.addNote(note);
      return Mono.just("Note added id : "+note.getId());
    }

    @GetMapping
    public Flux<Note> getNotes(){
        return noteService.getNotes();
    }

    @GetMapping("/id/{noteId}")
    public Mono<Note> getNoteById(@PathVariable String noteId){
        return noteService.getNoteById(noteId);
    }
}
