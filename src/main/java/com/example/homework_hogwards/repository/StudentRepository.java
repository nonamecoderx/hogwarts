package com.example.homework_hogwards.repository;

import com.example.homework_hogwards.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);
}
