package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.TeacherDTO;

import java.util.List;

public interface TeacherService extends DefaultService<TeacherDTO> {

    void addTeacherToFaculty(Integer facultyID, Integer teacherID);

    List<FacultyDTO> getFacultiesByTeacher(TeacherDTO teacherDTO);
}
