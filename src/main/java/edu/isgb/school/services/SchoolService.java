package edu.isgb.school.services;

import edu.isgb.school.entities.Course;
import edu.isgb.school.entities.Instructor;
import edu.isgb.school.entities.School;
import edu.isgb.school.entities.Student;

import java.util.List;

public interface SchoolService {
    School createSchool(School school);
    School getSchoolById(Integer id);
    Student createStudent(Student student, Integer schoolId);
    List<Student> getAllStudents();
    Instructor createInstructor(Instructor instructor, Integer schoolId);
    List<Instructor> getInstructorsByName(String name);
    Instructor getInstructorById(Integer id);
    Course getCourseById(Integer id);
    List<Course> getCoursesByInstructorId(Integer instructorId);
    Instructor addCourseToInstructor(Integer instructorId, Course course);


}
