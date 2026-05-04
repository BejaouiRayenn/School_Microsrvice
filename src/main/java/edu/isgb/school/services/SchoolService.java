package edu.isgb.school.services;

import edu.isgb.school.entities.*;
import edu.isgb.school.exeptions.ResourceNotFoundException;
import edu.isgb.school.repository.CourseRepository;
import edu.isgb.school.repository.InstructorRepository;
import edu.isgb.school.repository.SchoolRepository;
import edu.isgb.school.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor


public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    @Autowired
    private CourseRepository courseRepository;


    public School createSchool(School school) {
        if (school.getDepartments() != null) {
            school.getDepartments().forEach(dept -> dept.setSchool(school));
        }
        if (school.getStudent() != null) {
            school.getStudent().forEach(student -> student.setSchool(school));
        }
        if (school.getInstructor() != null) {
            school.getInstructor().forEach(instructor -> instructor.setSchool(school));
        }
        return schoolRepository.save(school);
    }



    @Transactional(readOnly = true)
    public School getSchoolById(Integer id) {
        return schoolRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pas de school avec l'ID : " + id));
    }


    public Student createStudent(Student student, Integer schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pas de school avec l'ID :  " + schoolId));
        student.setSchool(school);
        Student saved = studentRepository.save(student);
        school.getStudent().add(saved);
        return studentRepository.findById(saved.getIdStudent())
                .orElse(saved);
    }


    @Transactional(readOnly = true)
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }


    public Instructor createInstructor(Instructor instructor, Integer schoolId) {
        School school = schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Pas de school avec l'ID  : " + schoolId));
        instructor.setSchool(school);
        Instructor saved = instructorRepository.save(instructor);
        school.getInstructor().add(saved);
        return saved;
    }



    @Transactional(readOnly = true)
    public List<Instructor> getInstructorsByName(String name) {
        return instructorRepository.findByName(name);
    }



    @Transactional(readOnly = true)
    public Instructor getInstructorById(Integer id) {
        return instructorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor non trouvé avec l'id : " + id));
    }



    @Transactional(readOnly = true)
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Course non trouvé avec l'id : " + id));
    }



    @Transactional(readOnly = true)
    public List<Course> getCoursesByInstructorId(Integer instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor non trouvé avec l'id : " + instructorId));
        return instructor.getCourse();
    }



    public Instructor addCourseToInstructor(Integer instructorId, Course course) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Instructor non trouvé avec l'id : " + instructorId));
        Course savedCourse = courseRepository.save(course);
        instructor.getCourse().add(savedCourse);
        savedCourse.getInstructor().add(instructor);
        return instructorRepository.save(instructor);
    }
}
