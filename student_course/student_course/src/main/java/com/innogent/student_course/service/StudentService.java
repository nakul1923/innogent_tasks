package com.innogent.student_course.service;

import com.innogent.student_course.dto.CourseCountDto;
import com.innogent.student_course.dto.StudentDto;
import com.innogent.student_course.entity.Course;
import com.innogent.student_course.entity.Student;
import com.innogent.student_course.exception.customException.ResourceNotFoundException;
import com.innogent.student_course.mapper.StudentMapper;
import com.innogent.student_course.repository.CourseRepository;
import com.innogent.student_course.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final StudentMapper studentMapper;

    public StudentDto createStudent(StudentDto dto){

        Student s = studentMapper.toEntity(dto);
        if(dto.getCourse()!=null && dto.getCourse().getId() != null){

            Course course = courseRepository.findById(dto.getCourse().getId()).orElseThrow(()-> new ResourceNotFoundException("no course found"));
            s.setCourse(course);
        }

        Student saved = studentRepository.save(s);
        return studentMapper.toDto(saved);
    }

    public List<StudentDto> getAllStudentsWithCourse(){

        return studentRepository.findAllWithCourse().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsByCourseName(String courseName){

        Course c = courseRepository.findByName(courseName);
        if(c==null){

            throw new ResourceNotFoundException("no Course found with this name : " + courseName);
        }

        return studentRepository.findByCourse_Name(courseName).stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public List<StudentDto> getStudentsNotEnrolled() {
        return studentRepository.findStudentsNotEnrolled().stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public List<StudentDto> searchByCityAndInstructor(String city, String instructor) {
        return studentRepository.searchByCityAndCourseInstructor(city, instructor).stream().map(studentMapper::toDto).collect(Collectors.toList());
    }

    public CourseCountDto getCourseDetailsWithCount(Long courseId) {
        Course c = courseRepository.findById(courseId).orElseThrow(() -> new ResourceNotFoundException("no Course found with this Id : " + courseId));
        long count = c.getStudents().size();
        return CourseCountDto.builder().courseId(c.getId()).courseName(c.getName()).studentCount(count).build();
    }
}
