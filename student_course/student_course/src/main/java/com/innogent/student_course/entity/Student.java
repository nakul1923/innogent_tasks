package com.innogent.student_course.entity;


import lombok.*;
import jakarta.persistence.*;
@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)            // It will not allow nul values in name
    private String name;
    private String city;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;            // it can be null if null then student not enrolled in any course
}
