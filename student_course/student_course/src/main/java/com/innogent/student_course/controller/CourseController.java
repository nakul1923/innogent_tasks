package com.innogent.student_course.controller;

import com.innogent.student_course.dto.CourseCountDto;
import com.innogent.student_course.dto.CourseDto;
import com.innogent.student_course.service.CourseService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDto> createCourse(@RequestBody CourseDto dto){

        return ResponseEntity.ok(courseService.createCourse(dto));
    }

    @PutMapping("/{id}/instructor")
    public ResponseEntity<CourseDto> updateInstructor(@PathVariable Long id,@RequestBody String instructor){

        return ResponseEntity.ok(courseService.updateInstructor(id,instructor));
    }

    @GetMapping("/without-students")
    public ResponseEntity<List<CourseDto>> courseWithoutStudents(){

        return ResponseEntity.ok(courseService.getCourseWithoutStudents());
    }

    @GetMapping("/counts")
    public ResponseEntity<List<CourseCountDto>> courseCounts() {
        return ResponseEntity.ok(courseService.getCourseStudentCounts());
    }

    @GetMapping("/top")
    public ResponseEntity<List<CourseDto>> topCourses(@RequestParam(defaultValue = "5") int n) {
        return ResponseEntity.ok(courseService.getTopNCourses(n));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.getCourseById(id));
    }
}
