package com.example.jpa.services;

import com.example.jpa.models.db.Course;
import com.example.jpa.repositories.CourseRepository;
import graphql.schema.DataFetcher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public DataFetcher<List<Course>> getCourses() {
        return env -> courseRepository.findAll();
    }

    public DataFetcher<Course> getCourse() {
        return env -> {
            int id = env.getArgument("id");
            return courseRepository.findById((long) id).orElse(null);
        };
    }
}
