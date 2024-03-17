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
public class TeacherRespDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<FacultyDTO> faculties;
}
