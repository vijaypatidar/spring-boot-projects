package com.example.webflux.repositories;

import com.example.webflux.models.Note;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class NoteRepository {
    private final ReactiveMongoTemplate mongoTemplate;

    public NoteRepository(ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<Note> addNote(Note note){
        return mongoTemplate.save(note);
    }

    public Flux<Note> getNotes(){
        return mongoTemplate.findAll(Note.class);
    }

    public Mono<Note> getNoteById(String id) {
        return mongoTemplate.findById(id,Note.class);
    }
}
