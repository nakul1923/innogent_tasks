package com.innogent.student_course.mapper;

import com.innogent.student_course.dto.CourseDto;
import com.innogent.student_course.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    public CourseDto toDto(Course c) {
        if (c == null) return null;
        return CourseDto.builder()
                .id(c.getId())
                .name(c.getName())
                .instructor(c.getInstructor())
                .build();
    }


    public Course toEntity(CourseDto dto) {
        if (dto == null) return null;
        Course c = new Course();
        c.setId(dto.getId());
        c.setName(dto.getName());
        c.setInstructor(dto.getInstructor());
        return c;
    }
}
