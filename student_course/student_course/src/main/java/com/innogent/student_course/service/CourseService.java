package com.innogent.student_course.service;

import com.innogent.student_course.dto.CourseCountDto;
import com.innogent.student_course.dto.CourseDto;
import com.innogent.student_course.entity.Course;
import com.innogent.student_course.exception.customException.ResourceNotFoundException;
import com.innogent.student_course.mapper.CourseMapper;
import com.innogent.student_course.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor                 // learn
@Transactional                     // learn
public class CourseService {

    private final CourseRepository courseRepository;                 // (we have made it private, so that it can only accessible in this class only, and final so that no one can change it)
    private final CourseMapper courseMapper;

    public CourseDto createCourse(CourseDto dto){

        Course c = courseMapper.toEntity(dto);
        Course saved = courseRepository.save(c);
        return courseMapper.toDto(saved);
    }

    public CourseDto updateInstructor(Long courseId, String instructor){

        Course c = courseRepository.findById(courseId).orElseThrow(()->new ResourceNotFoundException("No course found with this id " + courseId));
        c.setInstructor(instructor);
        return courseMapper.toDto(courseRepository.save(c));

    }

    public List<CourseDto> getCourseWithoutStudents(){

        return courseRepository.findCoursesWithoutStudents().stream().map(courseMapper::toDto).collect(Collectors.toList());       // learn why we use ::

    }

    public List<CourseCountDto> getCourseStudentCounts(){

        List<Object[]> raw = courseRepository.findCourseStudentCounts();          // learn what this line is doing

        return raw.stream().map(arr-> new CourseCountDto(((Number)arr[0]).longValue(),(String)arr[1],((Number)arr[2]).longValue())).collect(Collectors.toList());         // learn what this line is doing
    }

    public List<CourseDto> getTopNCourses(int n){

        return courseRepository.findTopCoursesByEnrollment(PageRequest.of(0,n)).stream().map(courseMapper::toDto).collect(Collectors.toList());        //learn pageRequest
    }

    public CourseDto getCourseById(Long id){

        return courseRepository.findById(id).map(courseMapper::toDto).orElseThrow(()->new ResourceNotFoundException("No course found with the given id " + id));      // learn this line
    }
}
