package com.example.hogwarts.service.impl;

import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.mapper.StudentMapper;
import com.example.hogwarts.model.repository.StudentRepo;
import com.example.hogwarts.service.StudentService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepo studentRepo;

    @Override
    public StudentDTO getByID(Integer id) {
        return StudentMapper.STUDENT_MAPPER.toDto(studentRepo.findByID(id));
    }

    @Override
    public List<StudentDTO> getALl() {
        return StudentMapper.STUDENT_MAPPER.toListDto(studentRepo.findAll());
    }

    @Override
    public boolean create(StudentDTO dto) {
        return studentRepo.save(StudentMapper.STUDENT_MAPPER.toEntity(dto));
    }

    @Override
    public boolean update(StudentDTO dto) {
        return studentRepo.update(StudentMapper.STUDENT_MAPPER.toEntity(dto));
    }

    @Override
    public boolean remove(Integer id) {
        return studentRepo.delete(id);
    }
}
