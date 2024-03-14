package com.example.hogwarts.model.repository;

import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Teacher;

import java.util.List;

public interface TeacherRepo extends DefaultRepo<Teacher> {

    Teacher findByFirstNameAndLastName(Teacher teacher);

    List<Faculty> findFacultiesByTeacher(Teacher teacher);

    void addTeacherToFaculty(Faculty faculty, Teacher teacher);
}
