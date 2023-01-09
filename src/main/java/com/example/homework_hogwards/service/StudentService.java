package com.example.homework_hogwards.service;

import com.example.homework_hogwards.exception.InvalidIdException;
import com.example.homework_hogwards.exception.NotFoundException;
import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class StudentService {
    private final StudentRepository studentRepository;

    private final Logger logger;
    public StudentService (StudentRepository studentRepository){
        this.logger = LoggerFactory.getLogger(FacultyService.class);
        this.studentRepository=studentRepository;
    }

    public Student createStudent(Student student) {
        logger.info("Was invoked method for student createFaculty");
      return this.studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.info("Was invoked method for student getStudentById");
        return studentRepository.findById(studentId).orElseThrow(NotFoundException::new);
    }

    public Student updateStudent(Long studentId, Student student) {
        logger.info("Was invoked method for student updateStudent");
        Student oldStudent = studentRepository.findById(student.getId()).orElseThrow(InvalidIdException::new);
        return studentRepository.save(oldStudent.fillByStudent(student));
    }

    public Student deleteStudent(Long studentId) {
        logger.info("Was invoked method for student deleteStudent");
        studentRepository.deleteById(studentId);
        return null;
    }

    public List<Student> findByAge(int age) {
        logger.info("Was invoked method for student findByAge");
        return studentRepository.findByAge(age);
    }

    public List<Student> getAll() {
        logger.info("Was invoked method for student getAll");
        return studentRepository.findAll();
    }
    public List<Student> findByAgeBetween(int fromAge, int toAge) {
        logger.info("Was invoked method for student findByAgeBetween");
        return studentRepository.findByAgeBetween(fromAge, toAge);
    }

    public Faculty getStudentsFaculty(Long studentId) {
        logger.info("Was invoked method for student getStudentsFaculty");
        Student student = studentRepository.findById(studentId).orElseThrow(() -> {
            logger.error("There is not student with id = " + studentId);
            return new NotFoundException();
        });
        return student.getFaculty();
    }
    public List<String> findStudentsStartNameBySymbol(String symbol) {
        return studentRepository.findByNameStartingWithIgnoreCase(symbol)
                .stream()
                .map(s -> s.getName().toUpperCase())
                .sorted()
                .collect(Collectors.toList());
    }

    public Double getStudentsAvgAge() {
        return studentRepository.findAll().stream()
                .mapToInt(Student::getAge)
                .average()
                .orElse(0)
                ;
    }
}