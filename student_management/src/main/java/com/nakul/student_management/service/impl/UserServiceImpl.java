package com.nakul.student_management.service.impl;

import com.nakul.student_management.dto.UserDto;
import com.nakul.student_management.entity.User;
import com.nakul.student_management.exception.ResourceNotFoundException;
import com.nakul.student_management.mapper.UserMapper;
import com.nakul.student_management.repository.UserRepository;
import com.nakul.student_management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper){

        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }
    @Override
    public UserDto createUser(UserDto userDto) {

        User user = userMapper.toEntity(userDto);

        User savedUser = userRepository.save(user);

        return userMapper.toDto(savedUser);

   }

    @Override
    public UserDto getUserById(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No user found with this id " + id));

        return userMapper.toDto(user);
    }

    @Override
    public List<UserDto> getAllUsers() {

        List<User> users = userRepository.findAll();

        return users.stream().map(userMapper::toDto).toList();

    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {

        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("no User found with this id"));

        user.setFirstName(userDto.firstName() != null && !userDto.firstName().isEmpty() ? userDto.firstName() : user.getFirstName());
        user.setLastName(userDto.lastName() != null && !userDto.lastName().isEmpty() ? userDto.lastName() : user.getLastName());
        user.setEmail(userDto.email() != null && !userDto.email().isEmpty() ? userDto.email() : user.getEmail());

        User updatedUser = userRepository.save(user);
        return userMapper.toDto(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No user found with this id " + id));

        userRepository.delete(user);

    }


}
