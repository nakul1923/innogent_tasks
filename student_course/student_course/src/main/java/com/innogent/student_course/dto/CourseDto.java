package com.innogent.student_course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data                       //learn
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDto {

    private Long id;
    private String name;
    private String instructor;
}
