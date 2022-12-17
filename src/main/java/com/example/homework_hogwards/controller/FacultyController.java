package com.example.homework_hogwards.controller;

import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.service.FacultyService;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public List<Faculty> getAll() {
        return facultyService.getAll();
    }

    @PostMapping("/create")
    public Faculty createFaculty(@RequestBody Faculty student) {
        return facultyService.createFaculty(student);
    }

    @GetMapping("{studentId}")
    public Faculty getFaculty(@PathVariable Long studentId) {
        return facultyService.getFacultyById(studentId);
    }

    @PutMapping("/update")
    public Faculty updateFaculty(@RequestBody Faculty student) {
        return facultyService.updateFaculty(student.getId(), student);
    }

    @DeleteMapping("/delete/{studentId}")
    public Faculty delete(@PathVariable Long studentId) {
        return facultyService.deleteFaculty(studentId);
    }

    @GetMapping("/searchByColor")
    public Optional<Faculty> findByColor(@RequestParam String color) {
        return this.facultyService.findByColor(color);
    }
    @GetMapping("/findByColorContainingIgnoreCase")
    public Optional<Faculty> findByColorContainingIgnoreCase(@RequestParam String color) {
        return facultyService.findByColorContainingIgnoreCase(color);
    }
    @GetMapping("/findByNameContainingIgnoreCase")
    public Optional<Faculty> findByNameContainingIgnoreCase(@RequestParam String name) {
        return facultyService.findByNameContainingIgnoreCase(name);
    }

    @GetMapping("/{facultyId}/students")
    public Set<Student> getStudentsFaculties(@PathVariable Long facultyId) {
        return facultyService.getFacultyStudents(facultyId);
    }
}