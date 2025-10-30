package com.nakul.student_management.dto;

public record UserDto(Long id,
                      String firstName,
                      String lastName,
                      String email) {
}
