package com.example.jpa.repositories;

import com.example.jpa.models.db.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}
