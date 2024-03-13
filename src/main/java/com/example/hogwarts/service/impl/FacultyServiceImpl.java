package com.example.hogwarts.service.impl;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.service.FacultyService;
import lombok.RequiredArgsConstructor;


import java.util.List;

@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepo facultyRepo;

    @Override
    public FacultyDTO getByID(Integer id) {
        return FacultyMapper.FACULTY_MAPPER.toDto(facultyRepo.findByID(id));
    }

    @Override
    public List<FacultyDTO> getALl() {
        return FacultyMapper.FACULTY_MAPPER.toListDto(facultyRepo.findAll());
    }

    @Override
    public List<TeacherDTO> getTeachersByFaculty(FacultyDTO facultyDTO) {
        return TeacherMapper.TEACHER_MAPPER.toListDto(facultyRepo.getTeachersByFaculty(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO)));
    }

    @Override
    public List<StudentDTO> getStudentsByFaculty(FacultyDTO facultyDTO) {
        return StudentMapper.STUDENT_MAPPER.toListDto(facultyRepo.getStudentsByFaculty(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO)));
    }

    @Override
    public void save(FacultyDTO dto) {
        facultyRepo.save(FacultyMapper.FACULTY_MAPPER.toEntity(dto));
    }

    @Override
    public void update(FacultyDTO dto) {
        facultyRepo.update(FacultyMapper.FACULTY_MAPPER.toEntity(dto));
    }

    @Override
    public void delete(Integer id) {
        facultyRepo.delete(id);
    }
}
