package com.example.homework_hogwards.repository;

import com.example.homework_hogwards.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.crypto.spec.OAEPParameterSpec;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    List<Student> findByAge(int age);

    Optional<Student> findByAgeBetween(int fromAge, int toAge);
}
