package com.example.homework_hogwards.service;

import com.example.homework_hogwards.exception.InvalidIdException;
import com.example.homework_hogwards.exception.NotFoundException;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService (StudentRepository studentRepository){
        this.studentRepository=studentRepository;
    }

    public Student createStudent(Student student) {
      return this.studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).orElseThrow(NotFoundException::new);
    }

    public Student updateStudent(Long studentId, Student student) {
        Student oldStudent = studentRepository.findById(student.getId()).orElseThrow(InvalidIdException::new);
        return studentRepository.save(oldStudent.fillByStudent(student));
    }

    public Student deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
        return null;
    }

    public List<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public List<Student> getAll() {
        return studentRepository.findAll();
    }
}