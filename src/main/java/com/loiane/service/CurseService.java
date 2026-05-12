package com.loiane.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.CoursePageDTO;
import com.loiane.dto.mapper.CouseMapper;
import com.loiane.exeption.RecordNotFoundException;
import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Validated
@Service
public class CurseService {
    private final CourseRepository courseRepository;
    private final CouseMapper courseMapper;
    
    public CurseService(CourseRepository courseRepository, CouseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    
    // public List<CourseDTO> list() {
    //     return courseRepository.findAll().stream()
    //         .map(courseMapper::toDTO).toList();
    // }

    public CoursePageDTO list(@PositiveOrZero int page, @Positive @Max(100) int size) {
        Page<Course> pageResult = courseRepository.findAll(PageRequest.of(page, size));

        List<CourseDTO> courseDTOs = pageResult.getContent().stream()
            .map(courseMapper::toDTO)
            .toList();
        
        return new CoursePageDTO(courseDTOs, pageResult.getTotalElements(), pageResult.getTotalPages());
    }

    public CourseDTO getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@NotNull @Positive Long id, @Valid @NotNull CourseDTO courseDTO) {
        return courseRepository.findById(id)
            .map(existingCourse -> {
                Course course = courseMapper.toEntity(courseDTO);
                existingCourse.setName(courseDTO.name());
                existingCourse.setCategory(courseMapper.convertCategoryValue(courseDTO.category()));
                existingCourse.getLessons().clear();
                course.getLessons().forEach(existingCourse.getLessons()::add);
                return courseMapper.toDTO(courseRepository.save(existingCourse));
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
