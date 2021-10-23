package com.example.webflux;

import com.example.webflux.models.Note;
import com.example.webflux.services.NoteService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.Date;
import java.util.stream.IntStream;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxApplication {


    public static void main(String[] args) {
        SpringApplication.run(WebfluxApplication.class, args);
    }

    @Bean
    CommandLineRunner getCommandLineRunner(NoteService noteService) {
        return args -> IntStream
                .iterate(1, value -> value + 1)
                .limit(100).mapToObj(n -> new Note(
                        n + "",
                        new Date(),
                        "Title" + n,
                        "This is dummy"
                ))
                .forEach(noteService::addNote);
    }
}
