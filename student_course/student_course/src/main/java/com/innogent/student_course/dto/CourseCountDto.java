package com.innogent.student_course.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseCountDto {
    private Long courseId;
    private String courseName;
    private Long studentCount;
}
