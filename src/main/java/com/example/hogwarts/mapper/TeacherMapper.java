package com.example.hogwarts.mapper;

import com.example.hogwarts.dto.TeacherDTO;
import com.example.hogwarts.model.entity.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TeacherMapper {
    TeacherMapper INSTANCE = Mappers.getMapper(TeacherMapper.class);

    TeacherDTO toDto(Teacher teacher);

    List<TeacherDTO> toListDto(List<Teacher> teachers);

    Teacher toEntity(TeacherDTO teacherDTO);
}
