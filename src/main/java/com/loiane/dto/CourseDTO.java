package com.loiane.dto;

import java.util.List;
import java.util.Locale.Category;

import org.hibernate.validator.constraints.Length;

import com.loiane.enums.validation.ValueOfEnum;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record CourseDTO(
    Long id,
    @NotBlank @NotNull @Length(min= 5, max = 200) String name,
    @NotNull @Length(max = 20) @ValueOfEnum(enumClass = Category.class) String category,
    @NotNull @NotEmpty @Valid List<LessonDTO> lessons) {
}
