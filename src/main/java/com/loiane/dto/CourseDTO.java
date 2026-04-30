package com.loiane.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CourseDTO(
    Long id,
    @NotBlank @NotNull @Length(min= 5, max = 200) String name,
    @NotNull @Length(max = 20) @Pattern(regexp = "BACK-END|FRONT-END") String category) {
}
