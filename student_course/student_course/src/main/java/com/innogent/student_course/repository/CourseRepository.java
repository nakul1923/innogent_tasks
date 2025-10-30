package com.innogent.student_course.repository;

import com.innogent.student_course.entity.Course;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Long> {

    Course findByName(String name);


    @Query("SELECT c FROM Course c LEFT JOIN c.students s WHERE s IS NULL")
    List<Course> findCoursesWithoutStudents();


    @Query("SELECT c.id AS courseId, c.name AS courseName, COUNT(s.id) AS studentCount FROM Course c LEFT JOIN c.students s GROUP BY c.id, c.name")
    List<Object[]> findCourseStudentCounts();


    @Query("SELECT c FROM Course c LEFT JOIN c.students s GROUP BY c.id ORDER BY COUNT(s.id) DESC")
    List<Course> findTopCoursesByEnrollment(Pageable pageable);           // It is used for the pagination
}
