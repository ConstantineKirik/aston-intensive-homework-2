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
        return StudentMapper.INSTANCE.toDto(studentRepo.findByID(id));
    }

    @Override
    public List<StudentDTO> getALl() {
        return StudentMapper.INSTANCE.toListDto(studentRepo.findAll());
    }

    @Override
    public void save(StudentDTO dto) {
        studentRepo.save(StudentMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void update(StudentDTO dto) {
        studentRepo.update(StudentMapper.INSTANCE.toEntity(dto));
    }

    @Override
    public void delete(Integer id) {
        studentRepo.delete(id);
    }
}
