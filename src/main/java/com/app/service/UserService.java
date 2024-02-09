package com.app.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.DTO.UserDTO;
import com.app.exceptions.UserNotFoundException;
import com.app.model.User;
import com.app.repository.UserRepository;


@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(UserDTO userDTO) {
        User user = new User();
        // set other properties from userDTO
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        // set other properties

        return userRepository.save(user);
    }

    public User updateUser(Long userId, UserDTO updatedUserDTO) {
        User existingUser = getUserById(userId);

        // update fields
        existingUser.setUsername(updatedUserDTO.getUsername());
        existingUser.setEmail(updatedUserDTO.getEmail());
        // set other properties

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);
        userRepository.delete(user);
    }
}
