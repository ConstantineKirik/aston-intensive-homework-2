package com.example.hogwarts.service.impl;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.mapper.FacultyMapper;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.mapper.TeacherMapper;
import com.example.hogwarts.model.repository.FacultyRepo;
import com.example.hogwarts.model.repository.impl.FacultyRepoImpl;
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
        return TeacherMapper.TEACHER_MAPPER.toListDto(facultyRepo.findTeachersByFaculty(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO)));
    }

    @Override
    public List<StudentDTO> getStudentsByFaculty(FacultyDTO facultyDTO) {
        return StudentMapper.STUDENT_MAPPER.toListDto(facultyRepo.findStudentsByFaculty(FacultyMapper.FACULTY_MAPPER.toEntity(facultyDTO)));
    }

    @Override
    public boolean create(FacultyDTO dto) {
        return facultyRepo.save(FacultyMapper.FACULTY_MAPPER.toEntity(dto));
    }

    @Override
    public boolean update(FacultyDTO dto) {
        return facultyRepo.update(FacultyMapper.FACULTY_MAPPER.toEntity(dto));
    }

    @Override
    public boolean remove(Integer id) {
        return facultyRepo.delete(id);
    }
}
