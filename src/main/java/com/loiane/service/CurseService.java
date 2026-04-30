package com.loiane.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.mapper.CouseMapper;
import com.loiane.exeption.RecordNotFoundException;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CurseService {
    private final CourseRepository courseRepository;
    private final CouseMapper courseMapper;
    
    public CurseService(CourseRepository courseRepository, CouseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    
    public List<CourseDTO> list() {
        return courseRepository.findAll().stream()
            .map(courseMapper::toDTO).toList();
    }

    public CourseDTO getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
            .map(existingCourse -> {
                existingCourse.setName(course.name());
                existingCourse.setCategory(courseMapper.convertCategoryValue(course.category()));
                return courseMapper.toDTO(courseRepository.save(existingCourse));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
