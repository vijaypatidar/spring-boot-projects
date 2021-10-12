package com.example.jpa;

import com.example.jpa.models.db.Course;
import com.example.jpa.models.db.Student;
import com.example.jpa.repositories.CourseRepository;
import com.example.jpa.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class JpaApplication {
    private final StudentRepository studentRepository;
    public final CourseRepository courseRepository;

    public JpaApplication(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            Student student = new Student();
            student.setEmail("vijay@example.com");
            student.setFirstName("Vijay");
            student.setLastName("Patidar");
            student.setAge(20);
            studentRepository.save(student);
            log.info("Student add : id "+student.getId());

            Course course = new Course();
            course.setCourseName("JAVA : GraphQL");
            course.setStudent(student);
            courseRepository.save(course);
            log.info("Course : id "+course.getId());
        };
    }
}
