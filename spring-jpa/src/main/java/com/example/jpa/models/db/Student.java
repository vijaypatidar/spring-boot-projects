package com.example.jpa.models.db;

import javax.persistence.*;

@Entity(name = "student")
@Table(
        name = "students",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "students_email_unique",
                        columnNames = "email"
                )
        }
)
public class Student {
    @Id
    @SequenceGenerator(
            name = "student_id_seq",
            allocationSize = 1,
            sequenceName = "student_id_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_id_seq"
    )
    private Long id;
    private String firstName;
    private String lastName;
    @Column(
            name = "email",
            columnDefinition = "varchar(50)",
            nullable = false
    )

    private String email;
    private Integer age;


    public Student() {

    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
