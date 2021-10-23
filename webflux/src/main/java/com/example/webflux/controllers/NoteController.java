package com.example.webflux.controllers;

import com.example.webflux.models.Note;
import com.example.webflux.parser.PrepareExcel;
import com.example.webflux.services.NoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/notes")
@Slf4j
public class NoteController {

    private final NoteService noteService;
    private final PrepareExcel prepareExcel;

    public NoteController(NoteService noteService, PrepareExcel prepareExcel) {
        this.noteService = noteService;
        this.prepareExcel = prepareExcel;
    }

    @PostMapping()
    public Mono<?> addNote(@RequestBody Note note) {
        log.info("Add note : " + note.toString());
        noteService.addNote(note);
        return Mono.just("Note added id : " + note.getId());
    }

    @GetMapping
    public Flux<Note> getNotes() {
        return noteService.getNotes();
    }

    @GetMapping("/id/{noteId}")
    public Mono<Note> getNoteById(@PathVariable String noteId) {
        return noteService.getNoteById(noteId);
    }

    @GetMapping(value = "/excel/{fileName}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Mono<ResponseEntity<byte[]>> getExcel(@PathVariable String fileName) {
        Flux<Note> noteFlux = noteService.getNotes();
        return prepareExcel
                .notesToExcelFile(noteFlux)
                .map(bytes -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentDispositionFormData("fileName", fileName+".xls");
                    return ResponseEntity
                            .ok()
                            .headers(headers)
                            .body(bytes);
                });

    }
}
