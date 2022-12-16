package com.example.homework_hogwards.service;

import com.example.homework_hogwards.exception.InvalidIdException;
import com.example.homework_hogwards.exception.NotFoundException;
import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.repository.FacultyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;
    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;

    }

    public Faculty createFaculty(Faculty faculty) {
        return facultyRepository.save(faculty);
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultyRepository.findById(facultyId).orElseThrow(NotFoundException::new);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        Faculty oldFaculty = facultyRepository.findById(faculty.getId()).orElseThrow(InvalidIdException::new);
        return facultyRepository.save(oldFaculty.fillByFaculty(faculty));
    }

    public Faculty deleteFaculty(Long facultyId) {
        facultyRepository.deleteById(facultyId);
        return null;
    }

    public List<Faculty> findByColor(String color) {
        return facultyRepository.findByColor(color);
    }

    public List<Faculty> getAll() {
        return facultyRepository.findAll();
    }
}
