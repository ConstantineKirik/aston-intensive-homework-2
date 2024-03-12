package com.example.hogwarts.model.repository;

import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Teacher;

import java.util.List;

public interface TeacherRepo extends DefaultRepo<Teacher> {
    void addTeacherToFaculty(Faculty faculty, Teacher teacher);
    List<Faculty> getFacultiesByTeacher(Teacher teacher);
}
