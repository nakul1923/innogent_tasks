package com.nakul.student_management.service;

import com.nakul.student_management.dto.UserDto;

import java.util.List;


public interface UserService {

    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id,UserDto userDto);

    void deleteUser(Long id);
}
