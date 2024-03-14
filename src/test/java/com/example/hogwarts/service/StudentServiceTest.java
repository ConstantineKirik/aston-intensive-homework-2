package com.example.hogwarts.service;

import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.repository.StudentRepo;
import com.example.hogwarts.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private StudentDTO studentDTO;
    private StudentRepo studentRepo;
    private StudentService studentService;


    @BeforeEach
    void setUp() {
        studentDTO = StudentDTO.builder().id(1).build();
        studentRepo = Mockito.mock(StudentRepo.class);
        studentService = new StudentServiceImpl(studentRepo);
    }

    @Test
    void getByIdTest() {
        Student expectedStudent = StudentMapper.STUDENT_MAPPER.toEntity(studentDTO);
        Mockito.when(studentRepo.findByID(Mockito.anyInt())).thenReturn(expectedStudent);

        Student actualStudent = StudentMapper.STUDENT_MAPPER.toEntity(studentService.getByID(1));

        assertEquals(expectedStudent, actualStudent);
    }

    @Test
    void getAllTest() {
        List<Student> expectedStudents = new ArrayList<>();
        Mockito.when(studentRepo.findAll()).thenReturn(expectedStudents);

        List<Student> actualStudents = StudentMapper.STUDENT_MAPPER.toListEntity(studentService.getALl());

        assertEquals(expectedStudents, actualStudents);
    }

    @Test
    void createTest() {
        studentService.create(studentDTO);
        Mockito.verify(studentRepo).save(StudentMapper.STUDENT_MAPPER.toEntity(studentDTO));
    }

    @Test
    void createTrueTest() {
        Mockito.when(studentRepo.save(Mockito.any())).thenReturn(true);

        boolean result = studentService.create(studentDTO);

        assertTrue(result);
    }

    @Test
    void createFalseTest() {
        Mockito.when(studentRepo.findByFirstNameAndLastName(Mockito.any())).thenReturn(StudentMapper.STUDENT_MAPPER.toEntity(studentDTO));

        boolean result = studentService.create(studentDTO);

        assertFalse(result);
    }

    @Test
    void updateTest() {
        studentService.update(studentDTO);
        Mockito.verify(studentRepo).update(StudentMapper.STUDENT_MAPPER.toEntity(studentDTO));
    }

    @Test
    void updateTrueTest() {
        Mockito.when(studentRepo.update(Mockito.any())).thenReturn(true);

        boolean result = studentService.update(studentDTO);

        assertTrue(result);
    }

    @Test
    void updateFalseTest() {
        Mockito.when(studentRepo.findByFirstNameAndLastName(Mockito.any())).thenReturn(null);

        boolean result = studentService.update(studentDTO);

        assertFalse(result);
    }

    @Test
    void removeTest() {
        studentService.remove(studentDTO.getId());
        Mockito.verify(studentRepo).delete(studentDTO.getId());
    }

    @Test
    void removeTrueTest() {
        Mockito.when(studentRepo.save(Mockito.any())).thenReturn(true);

        boolean result = studentService.create(studentDTO);

        assertTrue(result);
    }

    @Test
    void removeFalseTest() {
        Mockito.when(studentRepo.findByID(Mockito.anyInt())).thenReturn(null);

        boolean result = studentService.create(studentDTO);

        assertFalse(result);
    }
}
