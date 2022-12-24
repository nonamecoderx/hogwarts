package com.example.homework_hogwards.controller;

import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @PostMapping("/create")
    public Student createStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("{studentId}")
    public Student getStudent(@PathVariable Long studentId) {
        return studentService.getStudentById(studentId);
    }

    @PutMapping("/update")
    public Student updateStudent(@RequestBody Student student) {
        return studentService.updateStudent(student.getId(), student);
    }

    @DeleteMapping("/delete/{studentId}")
    public Student delete(@PathVariable Long studentId) {
        return studentService.deleteStudent(studentId);
    }

    @GetMapping("/find")
    public List<Student> find(@RequestParam int age) {
        return studentService.findByAge(age);
    }
    @GetMapping("/findByAgeBetween")
    public List<Student> findByAgeBetween(@RequestParam int fromAge, @RequestParam int toAge) {
        return studentService.findByAgeBetween(fromAge, toAge);
    }

    @GetMapping("/{studentId}/faculties")
    public Faculty getStudentsFaculties(@PathVariable Long studentId) {
        return studentService.getStudentsFaculty(studentId);
    }
}
