package br.edu.infnet.courseservice.repository;

import br.edu.infnet.courseservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long>{
}


