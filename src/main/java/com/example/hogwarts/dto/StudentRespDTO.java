package com.example.hogwarts.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentRespDTO {
    private Integer id;
    private String firstName;
    private String lastName;
    private String faculty;
}
