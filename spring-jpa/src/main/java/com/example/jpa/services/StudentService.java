package com.example.jpa.services;

import com.example.jpa.models.db.Student;
import com.example.jpa.repositories.StudentRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public DataFetcher<List<Student>> getStudents() {
        return env -> studentRepository.findAll();
    }

    public DataFetcher<Student> getStudent() {
        return env -> {
            int id = env.getArgument("id");
            return studentRepository.findById((long) id).orElse(null);
        };
    }
}
