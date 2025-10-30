package com.nakul.student_management.mapper;

import com.nakul.student_management.dto.UserDto;
import com.nakul.student_management.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserDto toDto(User user){

        return new UserDto(user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail());
    }

    public User toEntity(UserDto userDto){

        return new User(userDto.id(),
                userDto.firstName(),
                userDto.lastName(),
                userDto.email());
    }
}
