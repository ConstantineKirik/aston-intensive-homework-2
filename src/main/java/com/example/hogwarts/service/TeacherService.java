package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.TeacherDTO;

import java.util.List;

public interface TeacherService extends DefaultService<TeacherDTO> {

    void addTeacherToFaculty(FacultyDTO facultyDTO, TeacherDTO teacherDTO);

    List<FacultyDTO> findFacultiesByTeacher(TeacherDTO teacherDTO);
}
