package com.example.hogwarts.mapper;

import com.example.hogwarts.dto.FacultyDTO;
import com.example.hogwarts.model.entity.Faculty;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FacultyMapper {
    FacultyMapper FACULTY_MAPPER = Mappers.getMapper(FacultyMapper.class);

    FacultyDTO toDto(Faculty faculty);

    List<FacultyDTO> toListDto(List<Faculty> faculties);

    Faculty toEntity(FacultyDTO facultyDTO);

    List<Faculty> toListEntity(List<FacultyDTO> faculties);
}
