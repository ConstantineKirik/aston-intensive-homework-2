package com.example.hogwarts.model.repository;

import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Student;
import com.example.hogwarts.model.entity.Teacher;

import java.util.List;

public interface FacultyRepo extends DefaultRepo<Faculty> {
    List<Teacher> getTeachersByFaculty(Faculty faculty);
    List<Student> getStudentsByFaculty(Faculty faculty);
}
