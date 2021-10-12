package com.example.jpa.repositories;

import com.example.jpa.models.db.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
