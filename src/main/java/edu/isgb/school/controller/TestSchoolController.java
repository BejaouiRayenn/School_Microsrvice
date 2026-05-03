package edu.isgb.school.controller;

import edu.isgb.school.entities.Course;
import edu.isgb.school.entities.Instructor;
import edu.isgb.school.entities.School;
import edu.isgb.school.entities.Student;
import edu.isgb.school.exeptions.ResourceNotFoundException;
import edu.isgb.school.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TestSchoolController {
    private final SchoolService schoolService;
    @PostMapping("/schools")
    public ResponseEntity<School> createSchool(@RequestBody School school) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schoolService.createSchool(school));
    }
    @GetMapping("/schools/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }
    @PostMapping("/schools/{schoolId}/students")
    public ResponseEntity<Student> createStudent(
            @RequestBody Student student,
            @PathVariable Integer schoolId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schoolService.createStudent(student, schoolId));
    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(schoolService.getAllStudents());
    }

    @PostMapping("/schools/{schoolId}/instructors")
    public ResponseEntity<Instructor> createInstructor(
            @RequestBody Instructor instructor,
            @PathVariable Integer schoolId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schoolService.createInstructor(instructor, schoolId));
    }
    @GetMapping("/instructors")
    public ResponseEntity<List<Instructor>> getInstructorsByName(
            @RequestParam String name) {
        return ResponseEntity.ok(schoolService.getInstructorsByName(name));
    }
    @GetMapping("/instructors/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getInstructorById(id));
    }
    @GetMapping("/instructors/{id}/courses")
    public ResponseEntity<List<Course>> getCoursesByInstructor(
            @PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getCoursesByInstructorId(id));
    }
    @PostMapping("/instructors/{id}/courses")
    public ResponseEntity<Instructor> addCourseToInstructor(
            @PathVariable Integer id,
            @RequestBody Course course) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(schoolService.addCourseToInstructor(id, course));
    }
    @GetMapping("/courses/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer id) {
        return ResponseEntity.ok(schoolService.getCourseById(id));
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
