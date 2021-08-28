package com.microexam.classapi.dto;

import lombok.*;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
public class ClassDTO {
    private int classId;
    @NotBlank(message = "Class name is required!")
    private String className;
    @NotBlank(message = "Class description is required")
    private String classDescription;
    private Timestamp timestamp;
}
