package com.example.jpa;

import com.example.jpa.db.models.Course;
import com.example.jpa.db.models.Student;
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

    public static void main(String[] args) {
        SpringApplication.run(JpaApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository, CourseRepository courseRepository){
        return args -> {
            Student vijay  = new Student();
            vijay.setFirstName("Vijay");
            vijay.setLastName("Patidar");
            vijay.setAge(23);
            vijay.setEmail("vijay@example.com");
            repository.save(vijay);
            log.info("Student saved : id = "+vijay.getId());

            Course java = new Course();
            java.setCourseName("Java");
            java.setStudent(vijay);
            courseRepository.save(java);

        };
    }
}
