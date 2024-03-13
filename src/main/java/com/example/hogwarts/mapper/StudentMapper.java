package com.example.hogwarts.mapper;

import com.example.hogwarts.dto.StudentDTO;
import com.example.hogwarts.model.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface StudentMapper {
    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);

    StudentDTO toDto(Student student);

    List<StudentDTO> toListDto(List<Student> students);

    Student toEntity(StudentDTO studentDTO);
}
