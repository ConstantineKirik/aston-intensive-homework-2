package com.example.hogwarts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacultyRespDTO {
    private Integer id;
    private String title;
    private List<TeacherDTO> teachers;
    private List<StudentDTO> students;
}
