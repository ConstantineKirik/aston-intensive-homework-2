package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.mapper.TeacherMapper;
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
        Mockito.when(facultyRepo.findByID(Mockito.anyInt())).thenReturn(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO));

        FacultyDTO actualFacultyDTO = facultyService.getByID(1);

        assertEquals(facultyDTO, actualFacultyDTO);
    }

    @Test
    void getByIdNullTest() {
        Mockito.when(facultyRepo.findByID(Mockito.anyInt())).thenReturn(null);

        FacultyDTO actualFacultyDTO = facultyService.getByID(1);

        assertNull(actualFacultyDTO);
    }

    @Test
     void getAllTest() {
        List<FacultyDTO> expectedFaculties = new ArrayList<>();
        Mockito.when(facultyRepo.findAll()).thenReturn(FacultyMapper.FACULTY_MAPPER.toListEntity(expectedFaculties));

        List<FacultyDTO> actualFaculties = facultyService.getALl();

        assertEquals(expectedFaculties, actualFaculties);
    }

    @Test
     void getTeachersByFacultyTest(){
        List<TeacherDTO> expectedTeachers = new ArrayList<>();
        Mockito.when(facultyRepo.findTeachersByFaculty(Mockito.any())).thenReturn(TeacherMapper.TEACHER_MAPPER.toListEntity(expectedTeachers));

        List<TeacherDTO> actualTeachers = facultyService.getTeachersByFaculty(facultyDTO);

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
     void getStudentsByFacultyTest(){
        List<StudentDTO> expectedStudents = new ArrayList<>();
        Mockito.when(facultyRepo.findStudentsByFaculty(Mockito.any())).thenReturn(StudentMapper.STUDENT_MAPPER.toListEntity(expectedStudents));

        List<StudentDTO> actualStudents = facultyService.getStudentsByFaculty(facultyDTO);

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
        Mockito.when(facultyRepo.save(Mockito.any())).thenReturn(false);

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
        Mockito.when(facultyRepo.update(Mockito.any())).thenReturn(false);

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
        Mockito.when(facultyRepo.delete(Mockito.anyInt())).thenReturn(false);

        boolean result = facultyService.remove(facultyDTO.getId());

        assertFalse(result);
    }
}
