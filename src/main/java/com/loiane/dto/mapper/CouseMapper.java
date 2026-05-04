package com.loiane.dto.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.loiane.dto.CourseDTO;
import com.loiane.dto.LessonDTO;
import com.loiane.enums.Category;
import com.loiane.model.Course;

@Component
public class CouseMapper {
    
    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }

        List<LessonDTO> lessons = course.getLessons().stream()
            .map(lesson -> new LessonDTO(lesson.getId(), lesson.getName(), lesson.getYoutubeUrl()))
            .toList();

        return new CourseDTO(
            course.getId(), 
            course.getName(), 
            course.getCategory().getValue(),
            lessons
        );
    }
    
    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }

        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "Back-end" -> Category.BACK_END;
            case "Front-end" -> Category.FRONT_END;
            default -> throw new IllegalArgumentException("Categoria inválida: " + value);
        };
    }
}
