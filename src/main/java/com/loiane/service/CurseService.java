package com.loiane.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import com.loiane.exeption.RecordNotFoundException;
import com.loiane.model.Course;
import com.loiane.repository.CourseRepository;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Validated
@Service
public class CurseService {
    private final CourseRepository courseRepository;
    
    public CurseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
    
    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course getById(@NotNull @Positive Long id) {
        return courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@NotNull @Positive Long id, @Valid Course course) {
        return courseRepository.findById(id)
            .map(existingCourse -> {
                existingCourse.setName(course.getName());
                existingCourse.setCategory(course.getCategory());
                return courseRepository.save(existingCourse);
            }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id) {
        courseRepository.delete(courseRepository.findById(id)
            .orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
