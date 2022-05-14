package com.example.newdiaryapp.services;

import com.example.newdiaryapp.dtos.UserDto;
import com.example.newdiaryapp.exceptions.DiaryAppApplicationException;
import com.example.newdiaryapp.models.Diary;
import com.example.newdiaryapp.models.User;
import com.example.newdiaryapp.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Optional;


@Service
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserRepository userRepository;
    private ModelMapper mapper;
    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public UserDto createAccount(String email, String password) throws DiaryAppApplicationException {
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if(userOptional.isEmpty()) {
            User user = new User(email,password);
            User savedUser = userRepository.save(user);
            return mapper.map(savedUser, UserDto.class);
        }
        throw new DiaryAppApplicationException("email already exists");
    }

    @Override
    public Diary addDiary(@NotNull Long id, @NotNull Diary diary) throws DiaryAppApplicationException {
       User user = userRepository.findById(id).orElseThrow(() -> new DiaryAppApplicationException("User with this id does not exist"));
       user.addDiary(diary);
       userRepository.save(user);
       return diary;
    }

    @Override
    public User findById(Long userId) throws DiaryAppApplicationException {
        return userRepository.findById(userId).orElseThrow(() -> new DiaryAppApplicationException("user does not exist"));
    }

    @Override
    public boolean deleteUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @SneakyThrows
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }
}
