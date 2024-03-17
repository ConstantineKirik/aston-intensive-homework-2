package com.example.hogwarts.service.impl;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.model.repository.impl.TeacherRepoImpl;
import com.example.hogwarts.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;

    @Override
    public TeacherDTO getByID(Integer id) {
        return TeacherMapper.TEACHER_MAPPER.toDto(teacherRepo.findByID(id));
    }

    @Override
    public List<TeacherDTO> getALl() {
        return TeacherMapper.TEACHER_MAPPER.toListDto(teacherRepo.findAll());
    }

    @Override
    public List<FacultyDTO> getFacultiesByTeacher(TeacherDTO teacherDTO) {
        return FacultyMapper.FACULTY_MAPPER.toListDto(teacherRepo.findFacultiesByTeacher(TeacherMapper.TEACHER_MAPPER.toEntity(teacherDTO)));
    }

    @Override
    public boolean create(TeacherDTO dto) {
        return teacherRepo.save(TeacherMapper.TEACHER_MAPPER.toEntity(dto));
    }

    @Override
    public void addTeacherToFaculty(Integer facultyID, Integer teacherID) {
        teacherRepo.addTeacherToFaculty(facultyID, teacherID);
    }

    @Override
    public boolean update(TeacherDTO dto) {
        return teacherRepo.update(TeacherMapper.TEACHER_MAPPER.toEntity(dto));
    }

    @Override
    public boolean remove(Integer id) {
        return teacherRepo.delete(id);
    }
}
