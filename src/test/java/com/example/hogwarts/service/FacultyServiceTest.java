package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.entity.Teacher;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.service.impl.FacultyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FacultyServiceTest {

    private FacultyDTO facultyDTO;
    private FacultyRepo facultyRepo;
    private FacultyService facultyService;


    @BeforeEach
    void setUp() {
        facultyDTO = FacultyDTO.builder().id(1).title("Test").build();
        facultyRepo = Mockito.mock(FacultyRepo.class);
        facultyService = new FacultyServiceImpl(facultyRepo);
    }

    @Test
     void getByIdTest() {
        Faculty expectedFaculty = FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO);
        Mockito.when(facultyRepo.findByID(Mockito.anyInt())).thenReturn(expectedFaculty);

        Faculty actualFaculty = FacultyMapper.FACULTY_MAPPER.toEntity(facultyService.getByID(1));

        assertEquals(expectedFaculty, actualFaculty);
    }

    @Test
     void getAllTest() {
        List<Faculty> expectedFaculties = new ArrayList<>();
        Mockito.when(facultyRepo.findAll()).thenReturn(expectedFaculties);

        List<Faculty> actualFaculties = FacultyMapper.FACULTY_MAPPER.toListEntity(facultyService.getALl());

        assertEquals(expectedFaculties, actualFaculties);
    }

    @Test
     void getTeachersByFacultyTest(){
        List<Teacher> expectedTeachers = new ArrayList<>();
        Mockito.when(facultyRepo.findTeachersByFaculty(Mockito.any())).thenReturn(expectedTeachers);

        List<Teacher> actualTeachers = TeacherMapper.TEACHER_MAPPER.toListEntity(facultyService.getTeachersByFaculty(facultyDTO));

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
     void getStudentsByFacultyTest(){
        List<Student> expectedStudents = new ArrayList<>();
        Mockito.when(facultyRepo.findStudentsByFaculty(Mockito.any())).thenReturn(expectedStudents);

        List<Student> actualStudents = StudentMapper.STUDENT_MAPPER.toListEntity(facultyService.getStudentsByFaculty(facultyDTO));

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
     void createTest(){
        facultyService.create(facultyDTO);
        Mockito.verify(facultyRepo).save(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO));
    }

    @Test
    void createTrueTest() {
        Mockito.when(facultyRepo.save(Mockito.any())).thenReturn(true);

        boolean result = facultyService.create(facultyDTO);

        assertTrue(result);
    }

    @Test
    void createFalseTest() {
        Mockito.when(facultyRepo.findByTitle(Mockito.any())).thenReturn(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO));

        boolean result = facultyService.create(facultyDTO);

        assertFalse(result);
    }

    @Test
     void updateTest(){
        facultyService.update(facultyDTO);
        Mockito.verify(facultyRepo).update(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO));
    }

    @Test
    void updateTrueTest() {
        Mockito.when(facultyRepo.update(Mockito.any())).thenReturn(true);

        boolean result = facultyService.update(facultyDTO);

        assertTrue(result);
    }

    @Test
    void updateFalseTest() {
        Mockito.when(facultyRepo.findByTitle(Mockito.any())).thenReturn(null);

        boolean result = facultyService.update(facultyDTO);

        assertFalse(result);
    }

    @Test
     void removeTest(){
        facultyService.remove(facultyDTO.getId());
        Mockito.verify(facultyRepo).delete(facultyDTO.getId());
    }

    @Test
    void removeTrueTest() {
        Mockito.when(facultyRepo.delete(Mockito.anyInt())).thenReturn(true);

        boolean result = facultyService.remove(facultyDTO.getId());

        assertTrue(result);
    }

    @Test
    void removeFalseTest() {
        Mockito.when(facultyRepo.findByID(Mockito.anyInt())).thenReturn(null);

        boolean result = facultyService.remove(facultyDTO.getId());

        assertFalse(result);
    }
}
