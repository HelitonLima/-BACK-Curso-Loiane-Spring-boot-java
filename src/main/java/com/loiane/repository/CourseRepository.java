package com.loiane.repository;

import org.springframework.stereotype.Repository;

import com.loiane.model.Course;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
}
