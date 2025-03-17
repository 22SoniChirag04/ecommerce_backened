package com.atoz.e_commerce.Services;

import com.atoz.e_commerce.Dao.UserDao;
import com.atoz.e_commerce.Exception.UserNotFoundException;
import com.atoz.e_commerce.Models.User;
import com.atoz.e_commerce.Repositary.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDao> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDao getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    public void addUser(UserDao user) {
        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("user is not added", e);
        }
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

}