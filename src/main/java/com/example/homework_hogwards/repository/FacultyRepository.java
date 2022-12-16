package com.example.homework_hogwards.repository;

import com.example.homework_hogwards.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyRepository extends JpaRepository <Faculty,Long>{
    List<Faculty> findByColor(String color);
}
