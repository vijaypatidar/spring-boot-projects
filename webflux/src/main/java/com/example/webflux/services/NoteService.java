package com.example.webflux.services;

import com.example.webflux.models.Note;
import com.example.webflux.repositories.NoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class NoteService {

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public void addNote(Note note) {
        Note block = this.noteRepository.addNote(note).block();
        assert block != null;
        log.info(block.toString());
    }

    public Mono<Note> getNoteById(@NonNull String id) {
        return noteRepository.getNoteById(id);
    }

    public Flux<Note> getNotes(){
        return noteRepository.getNotes();
    }

}
