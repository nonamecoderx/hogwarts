package com.example.homework_hogwards.service;

import com.example.homework_hogwards.exception.InvalidIdException;
import com.example.homework_hogwards.exception.NotFoundException;
import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.model.Student;
import com.example.homework_hogwards.repository.FacultyRepository;
import com.example.homework_hogwards.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    private final StudentRepository studentRepository;

    private final Logger logger;

    public FacultyService(
            FacultyRepository facultyRepository,
            StudentRepository studentRepository
    ) {
        this.facultyRepository = facultyRepository;
        this.studentRepository = studentRepository;
        this.logger = LoggerFactory.getLogger(FacultyService.class);
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.info("Was invoked method for faculty createFaculty");
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        logger.info("Was invoked method for faculty getFacultyById");
        return facultyRepository.findById(facultyId).orElseThrow(NotFoundException::new);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        logger.info("Was invoked method for faculty updateFaculty");
        Faculty oldFaculty = facultyRepository.findById(faculty.getId()).orElseThrow(InvalidIdException::new);
        return facultyRepository.save(oldFaculty.fillByFaculty(faculty));
    }

    public Faculty deleteFaculty(Long facultyId) {
        logger.info("Was invoked method for faculty deleteFaculty");
        facultyRepository.deleteById(facultyId);
        return null;
    }

    public Optional<Faculty> findByColor(String color) {
        logger.info("Was invoked method for faculty findByColor");
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getAll() {
        logger.info("Was invoked method for faculty getAll");
        return facultyRepository.findAll();
    }
    public Optional<Faculty> findByColorContainingIgnoreCase(String color) {
        logger.info("Was invoked method for faculty findByColorContainingIgnoreCase");
        return facultyRepository.findByColorContainingIgnoreCase(color);
    }
    public Optional<Faculty> findByNameContainingIgnoreCase(String name) {
        logger.info("Was invoked method for faculty findByNameContainingIgnoreCase");
        return facultyRepository.findByNameContainingIgnoreCase(name);
    }
    public Set<Student> getFacultyStudents(Long facultyId) {
        logger.info("Was invoked method for faculty getFacultyStudents");
        Faculty faculty = facultyRepository.findById(facultyId).orElseThrow(InvalidIdException::new);
        return faculty.getStudents();
    }
}
