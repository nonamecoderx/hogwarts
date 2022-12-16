package com.example.homework_hogwards.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String color;


    public String getName() {
        return name;
    }

    public Faculty setName(String name) {
        this.name = name;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Faculty setColor(String color) {
        this.color = color;
        return this;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Faculty faculty)) return false;
        return id.equals(faculty.id) && name.equals(faculty.name) && color.equals(faculty.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, color);
    }

    public Faculty fillByFaculty(Faculty faculty) {
        color = faculty.color;
        name = faculty.name;
        return this;
    }
}
