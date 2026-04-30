package com.loiane;

import com.loiane.enums.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.loiane.model.Course;
import com.loiane.model.Lesson;
import com.loiane.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			Course c = new Course();
			c.setName("Angular com Spring Boot");
			c.setCategory(Category.FRONT_END);

			Lesson lesson = new Lesson();

			lesson.setName("Introdução");
			lesson.setYoutubeUrl("Nb4uxLxdvxo");
			lesson.setCourse(c);
			c.getLessons().add(lesson);

			courseRepository.save(c);
		};
	}
}
