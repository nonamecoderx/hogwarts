package com.example.homework_hogwards;

import com.example.homework_hogwards.controller.FacultyController;
import com.example.homework_hogwards.model.Faculty;
import com.example.homework_hogwards.repository.AvatarRepository;
import com.example.homework_hogwards.repository.FacultyRepository;
import com.example.homework_hogwards.repository.StudentRepository;
import com.example.homework_hogwards.service.AvatarService;
import com.example.homework_hogwards.service.FacultyService;
import com.example.homework_hogwards.service.StudentService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class FacultyControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FacultyRepository facultyRepository;

    @MockBean
    private AvatarRepository avatarRepository;

    @MockBean
    private StudentRepository studentRepository;

    @SpyBean
    private FacultyService facultyService;

    @SpyBean
    private AvatarService avatarService;

    @SpyBean
    private StudentService studentService;

    @InjectMocks
    private FacultyController facultyController;

    @BeforeEach
    void setUp() {
        Faculty faculty = (new Faculty()).setColor("testColor").setName("testFaculty");
        when(facultyRepository.save(any(Faculty.class)))
                .thenReturn(faculty);
        when(facultyRepository.findById(any(Long.class)))
                .thenReturn(Optional.of(faculty));
    }

    @Test
    void testSaveFaculty() throws Exception {
        String name = "testFaculty";
        String color = "testColor";

        JSONObject facultyObject = new JSONObject();
        facultyObject.put("name", name);
        facultyObject.put("color", color);

        mockMvc.perform(
                        MockMvcRequestBuilders
                                .post("/faculty/create") //send
                                .content(facultyObject.toString())
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk()) //receive
                .andExpect(jsonPath("$.name").value(name))
                .andExpect(jsonPath("$.color").value(color));
    }
}