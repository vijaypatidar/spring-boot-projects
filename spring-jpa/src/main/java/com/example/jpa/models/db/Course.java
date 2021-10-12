package com.example.jpa.models.db;

import javax.persistence.*;

@Entity(name = "course")
@Table(
        name = "courses"
)
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "student_id",referencedColumnName = "id")
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
