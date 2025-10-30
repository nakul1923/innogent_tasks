package com.innogent.student_course.repository;

import com.innogent.student_course.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student,Long> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.course")
    List<Student> findAllWithCourse();


    List<Student> findByCourse_Name(String courseName);


    @Query("SELECT s FROM Student s WHERE s.course IS NULL")
    List<Student> findStudentsNotEnrolled();


    @Query("SELECT s FROM Student s JOIN s.course c WHERE s.city = :city AND c.instructor = :instructor")
    List<Student> searchByCityAndCourseInstructor(@Param("city") String city, @Param("instructor") String instructor);
}
