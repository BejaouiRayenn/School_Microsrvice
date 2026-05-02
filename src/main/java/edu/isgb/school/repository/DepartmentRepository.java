package edu.isgb.school.repository;

import edu.isgb.school.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository  extends JpaRepository<Department, Integer> {
}
