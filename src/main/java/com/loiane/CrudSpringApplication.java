package com.loiane;

import com.loiane.enums.Category;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import com.loiane.model.Course;
import com.loiane.model.Lesson;
import com.loiane.repository.CourseRepository;

@SpringBootApplication
public class CrudSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudSpringApplication.class, args);
	}

	@Bean
	@Profile("dev")
	CommandLineRunner initDatabase(CourseRepository courseRepository) {
		return args -> {
			courseRepository.deleteAll();

			for (int i = 0; i < 20; i++) {
				Course c = new Course();
				c.setName("Angular com Spring Boot " + i);
				c.setCategory(Category.FRONT_END);

				Lesson lesson = new Lesson();

				lesson.setName("Introdução");
				lesson.setYoutubeUrl("Nb4uxLxdvxo");
				lesson.setCourse(c);
				c.getLessons().add(lesson);

				courseRepository.save(c);
			}
		};
	}
}
