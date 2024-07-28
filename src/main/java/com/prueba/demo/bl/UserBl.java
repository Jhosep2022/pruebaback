package com.prueba.demo.bl;

import com.prueba.demo.dto.UserDto;
import com.prueba.demo.entity.User;
import com.prueba.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserBl {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    public UserDto getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(this::convertEntityToDto).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        User user = convertDtoToEntity(userDto);
        user = userRepository.save(user);
        return convertEntityToDto(user);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    private UserDto convertEntityToDto(User user) {
        return new UserDto(user.getId(), user.getName(), user.getEmail());
    }

    private User convertDtoToEntity(UserDto userDto) {
        return new User(userDto.getId(), userDto.getName(), userDto.getEmail());
    }
}
