package com.example.homework_hogwards.repository;

import com.example.homework_hogwards.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository <Faculty,Long>{
    Optional<Faculty> findByColor(String color);
    Optional<Faculty> findByColorContainingIgnoreCase(String color);
    Optional<Faculty> findByNameContainingIgnoreCase(String name);
}
