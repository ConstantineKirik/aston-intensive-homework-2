package com.example.hogwarts.service;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.dto.TeacherDTO;

import java.util.List;

public interface FacultyService extends DefaultService<FacultyDTO> {

    List<TeacherDTO> getTeachersByFaculty(FacultyDTO facultyDTO);

    List<StudentDTO> getStudentsByFaculty(FacultyDTO facultyDTO);
}
