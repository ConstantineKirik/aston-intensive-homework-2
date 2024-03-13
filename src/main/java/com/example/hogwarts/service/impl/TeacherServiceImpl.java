package com.example.hogwarts.service.impl;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.repository.TeacherRepo;
import com.example.hogwarts.service.TeacherService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepo teacherRepo;

    @Override
    public TeacherDTO getByID(Integer id) {
        return TeacherMapper.INSTANCE.toDto(teacherRepo.findByID(id));
    }

    @Override
    public List<TeacherDTO> getALl() {
        return TeacherMapper.INSTANCE.toListDto(teacherRepo.findAll());
    }

    @Override
    public List<FacultyDTO> findFacultiesByTeacher(TeacherDTO teacherDTO) {
        return FacultyMapper.INSTANCE.toListDto(teacherRepo.findFacultiesByTeacher(TeacherMapper.INSTANCE.toEntity(teacherDTO)));
    }

    @Override
    public void save(TeacherDTO dto) {
        teacherRepo.save(TeacherMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void addTeacherToFaculty(FacultyDTO facultyDTO, TeacherDTO teacherDTO) {
        teacherRepo.addTeacherToFaculty(FacultyMapper.INSTANCE.toEntity(facultyDTO), TeacherMapper.INSTANCE.toEntity(teacherDTO));
    }

    @Override
    public void update(TeacherDTO dto) {
        teacherRepo.update(TeacherMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(Integer id) {
        teacherRepo.delete(id);
    }
}
