package com.example.newdiaryapp.services;


import com.example.newdiaryapp.dtos.UserDto;
import com.example.newdiaryapp.exceptions.DiaryAppApplicationException;
import com.example.newdiaryapp.models.User;
import com.example.newdiaryapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceConcreteTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;


    @Test
    void testThatCanDeleteUser() throws DiaryAppApplicationException {
        UserDto userDto = userService.createAccount("user-user@gmail.com","password");
        User user = userRepository.findById(userDto.getId()).get();
        userService.deleteUser(user);
        Optional<User> fromDatabase = userRepository.findById(userDto.getId());
        assertThat(fromDatabase).isNull();
    }


}
