package com.innogent.student_course.controller;

import com.innogent.student_course.dto.CourseCountDto;
import com.innogent.student_course.dto.StudentDto;
import com.innogent.student_course.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto dto) {
        return ResponseEntity.ok(studentService.createStudent(dto));
    }


    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllWithCourse() {
        return ResponseEntity.ok(studentService.getAllStudentsWithCourse());
    }


    @GetMapping("/by-course")
    public ResponseEntity<List<StudentDto>> getByCourse(@RequestParam String name) {
        return ResponseEntity.ok(studentService.getStudentsByCourseName(name));
    }


    @GetMapping("/not-enrolled")
    public ResponseEntity<List<StudentDto>> notEnrolled() {
        return ResponseEntity.ok(studentService.getStudentsNotEnrolled());
    }


    @GetMapping("/search")
    public ResponseEntity<List<StudentDto>> searchByCityAndInstructor(@RequestParam String city, @RequestParam String instructor) {
        return ResponseEntity.ok(studentService.searchByCityAndInstructor(city, instructor));
    }


    @GetMapping("/course/{courseId}/details")
    public ResponseEntity<CourseCountDto> courseDetailsWithCount(@PathVariable Long courseId) {
        return ResponseEntity.ok(studentService.getCourseDetailsWithCount(courseId));
    }
}
