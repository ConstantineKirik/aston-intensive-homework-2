package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TeacherServiceTest {

    private TeacherDTO teacherDTO;
    private TeacherRepo teacherRepo;
    private TeacherService teacherService;


    @BeforeEach
    void setUp() {
        teacherDTO = TeacherDTO.builder().id(1).build();
        teacherRepo = Mockito.mock(TeacherRepo.class);
        teacherService = new TeacherServiceImpl(teacherRepo);
    }

    @Test
    void getByIdTest() {
        Mockito.when(teacherRepo.findByID(Mockito.anyInt())).thenReturn(TeacherMapper.TEACHER_MAPPER.toEntity(teacherDTO));

        TeacherDTO actualTeacherDTO = teacherService.getByID(1);

        assertEquals(teacherDTO, actualTeacherDTO);
    }

    @Test
    void getByIdNullTest() {
        Mockito.when(teacherRepo.findByID(Mockito.anyInt())).thenReturn(null);

        TeacherDTO actualTeacherDTO = teacherService.getByID(1);

        assertNull(actualTeacherDTO);
    }

    @Test
    void getAllTest() {
        List<TeacherDTO> expectedTeachers = new ArrayList<>();
        Mockito.when(teacherRepo.findAll()).thenReturn(TeacherMapper.TEACHER_MAPPER.toListEntity(expectedTeachers));

        List<TeacherDTO> actualTeachers = teacherService.getALl();

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    void getFacultiesByTeacherTest() {
        List<FacultyDTO> expectedTeachers = new ArrayList<>();
        Mockito.when(teacherRepo.findFacultiesByTeacher(Mockito.any())).thenReturn(FacultyMapper.FACULTY_MAPPER.toListEntity(expectedTeachers));

        List<FacultyDTO> actualTeachers = teacherService.getFacultiesByTeacher(teacherDTO);

        assertEquals(expectedTeachers, actualTeachers);
    }

    @Test
    void createTest() {
        teacherService.create(teacherDTO);
        Mockito.verify(teacherRepo).save(TeacherMapper.TEACHER_MAPPER.toEntity(teacherDTO));
    }

    @Test
    void createTrueTest() {
        Mockito.when(teacherRepo.save(Mockito.any())).thenReturn(true);

        boolean result = teacherService.create(teacherDTO);

        assertTrue(result);
    }

    @Test
    void createFalseTest() {
        Mockito.when(teacherRepo.save(Mockito.any())).thenReturn(false);

        boolean result = teacherService.create(teacherDTO);

        assertFalse(result);
    }

    @Test
    void addTeacherToFacultyTest(){
        FacultyDTO faculty = new FacultyDTO();
        TeacherDTO teacher = new TeacherDTO();

        teacherService.addTeacherToFaculty(faculty, teacher);
        Mockito.verify(teacherRepo).addTeacherToFaculty(FacultyMapper.FACULTY_MAPPER.toEntity(faculty),
                TeacherMapper.TEACHER_MAPPER.toEntity(teacher));
    }

    @Test
    void updateTest() {
        teacherService.update(teacherDTO);
        Mockito.verify(teacherRepo).update(TeacherMapper.TEACHER_MAPPER.toEntity(teacherDTO));
    }

    @Test
    void updateTrueTest() {
        Mockito.when(teacherRepo.update(Mockito.any())).thenReturn(true);

        boolean result = teacherService.update(teacherDTO);

        assertTrue(result);
    }

    @Test
    void updateFalseTest() {
        Mockito.when(teacherRepo.update(Mockito.any())).thenReturn(false);

        boolean result = teacherService.update(teacherDTO);

        assertFalse(result);
    }

    @Test
    void removeTest() {
        teacherService.remove(teacherDTO.getId());
        Mockito.verify(teacherRepo).delete(teacherDTO.getId());
    }

    @Test
    void removeTrueTest() {
        Mockito.when(teacherRepo.save(Mockito.any())).thenReturn(true);

        boolean result = teacherService.create(teacherDTO);

        assertTrue(result);
    }

    @Test
    void removeFalseTest() {
        Mockito.when(teacherRepo.delete(Mockito.any())).thenReturn(false);

        boolean result = teacherService.create(teacherDTO);

        assertFalse(result);
    }
}
