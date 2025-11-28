package com.rcoem.sms.application.services;

import com.rcoem.sms.application.dto.StudentDetails;
import com.rcoem.sms.application.dto.UserDetails;
import com.rcoem.sms.application.mapper.StudentMapper;
import com.rcoem.sms.application.mapper.UserMapper;
import com.rcoem.sms.domain.entities.StudentInfo;
import com.rcoem.sms.domain.entities.UserInfo;
import com.rcoem.sms.domain.repositories.StudentRepository;
import com.rcoem.sms.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.Objects.nonNull;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

@Autowired
StudentService studentService;


    @Override
    public UserDetails registerUser(UserDetails userDetails) {
        String uid = "USER" + UUID.randomUUID();
        userDetails.setId(uid);
        UserDetails userDetails1= userMapper.toDto(userRepository.save(userMapper.toEntity(userDetails)));
        if(userDetails.getType().equalsIgnoreCase("student")) {
            studentService.createStudent(StudentDetails.builder()
                    .id(userDetails.getId())
                    .name(userDetails.getName())
                    .email(userDetails.getEmail())
                    .gender(userDetails.getGender())
                    .points(0)
                    .build());
        }
        return userDetails1;
    }

    @Override
    public UserDetails signInUser(String email,String password) {
        UserInfo userDetails=userRepository.findByEmail(email)
                .orElseThrow(()-> new RuntimeException("User Not found"));
        if(nonNull(userDetails)){
            if(userDetails.getPassword().equalsIgnoreCase(password)){
                return userMapper.toDto(userDetails);
            }
            throw new RuntimeException("invalid credentials");
        }
        return null;
    }

    @Override
    public UserDetails getUserDetails(UserDetails userDetails) {
        return null;
    }

    @Override
    public void updateUserType(UserDetails userDetails) {

    }
}
