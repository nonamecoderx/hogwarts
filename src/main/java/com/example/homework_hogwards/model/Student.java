package com.example.homework_hogwards.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;
@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @ManyToOne
    @JsonManagedReference
    private Faculty faculty;

    public Student() {}

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Student setAge(int age) {
        this.age = age;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student student)) return false;
        return age == student.age && id.equals(student.id) && name.equals(student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    public Student fillByStudent(Student student) {
        name = student.getName();
        age = student.getAge();
        return this;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public Student setFaculty(Faculty faculty) {
        this.faculty = faculty;
        return this;
    }
}