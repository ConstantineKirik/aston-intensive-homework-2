package com.example.hogwarts.model.repository;

import com.example.hogwarts.model.entity.Faculty;
import com.example.hogwarts.model.entity.Teacher;

import java.util.List;

public interface TeacherRepo extends DefaultRepo<Teacher> {

    List<Faculty> findFacultiesByTeacher(Teacher teacher);

    void addTeacherToFaculty(Integer facultyID, Integer teacherID);
}
