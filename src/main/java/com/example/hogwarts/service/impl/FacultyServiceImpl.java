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
        return FacultyMapper.INSTANCE.toDto(facultyRepo.findByID(id));
    }

    @Override
    public List<FacultyDTO> getALl() {
        return FacultyMapper.INSTANCE.toListDto(facultyRepo.findAll());
    }

    @Override
    public List<TeacherDTO> getTeachersByFaculty(FacultyDTO facultyDTO) {
        return TeacherMapper.INSTANCE.toListDto(facultyRepo.getTeachersByFaculty(FacultyMapper.INSTANCE.toEntity(facultyDTO)));
    }

    @Override
    public List<StudentDTO> getStudentsByFaculty(FacultyDTO facultyDTO) {
        return StudentMapper.INSTANCE.toListDto(facultyRepo.getStudentsByFaculty(FacultyMapper.INSTANCE.toEntity(facultyDTO)));
    }

    @Override
    public void save(FacultyDTO dto) {
        facultyRepo.save(FacultyMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(FacultyDTO dto) {
        facultyRepo.update(FacultyMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(Integer id) {
        facultyRepo.delete(id);
    }
}
