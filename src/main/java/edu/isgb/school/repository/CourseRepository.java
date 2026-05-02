package edu.isgb.school.repository;

import edu.isgb.school.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface   CourseRepository extends JpaRepository<Course, Integer> {
}
