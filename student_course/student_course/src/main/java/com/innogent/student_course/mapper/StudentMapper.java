package com.innogent.student_course.mapper;

import com.innogent.student_course.dto.CourseDto;
import com.innogent.student_course.dto.StudentDto;
import com.innogent.student_course.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public StudentDto toDto(Student s) {
        if (s == null) return null;
        CourseDto cd = null;
        if (s.getCourse() != null) {
            cd = CourseDto.builder()
                    .id(s.getCourse().getId())
                    .name(s.getCourse().getName())
                    .instructor(s.getCourse().getInstructor())
                    .build();
        }
        return StudentDto.builder()
                .id(s.getId())
                .name(s.getName())
                .city(s.getCity())
                .course(cd)
                .build();
    }


    public Student toEntity(StudentDto dto) {
        if (dto == null) return null;
        Student s = new Student();
        s.setId(dto.getId());
        s.setName(dto.getName());
        s.setCity(dto.getCity());
// course mapping handled in service by loading course entity
        return s;
    }
}
