package com.example.homework_hogwards.controller;

import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Stream;

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

    @GetMapping("/age/avg")
    public ResponseEntity<Double> getStudentsAvgAge() {
        return ResponseEntity.ok(studentService.getStudentsAvgAge());
    }

    @GetMapping("/name/start")
    public List<String> getStudentsStartNameBySymbol(@RequestParam String symbol) {
        symbol = symbol.substring(0, 1);
        return studentService.findStudentsStartNameBySymbol(symbol);
    }
    @GetMapping("/returnInteger")
    Integer returnInteger(){
        int sum = Stream.iterate(1, a -> a +1).limit(1_000_000).parallel().reduce(0, Integer::sum);
        return sum;
    }
}
