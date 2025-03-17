package com.atoz.e_commerce.Controller;

import com.atoz.e_commerce.Dao.UserDao;
import com.atoz.e_commerce.Exception.UserNotFoundException;
import com.atoz.e_commerce.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private ResponseEntity<Map<String, Object>> createResponse(boolean success, String message, Object data, HttpStatus status) {
        Map<String, Object> response = new HashMap<>();
        // response.put("success", success);
        response.put("code", status.value());
        response.put("message", message);
        response.put("content", data);
        return new ResponseEntity<>(response, status);
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<UserDao> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return createResponse(false, "No users found", null, HttpStatus.NO_CONTENT);
        }
        return createResponse(true, "Users retrieved successfully", users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        try {
            UserDao user = userService.getUserById(id);
            return createResponse(true, "User found", user, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return createResponse(false, e.getMessage(), null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody UserDao user) {
        userService.addUser(user);
        return createResponse(true, "User created successfully", user, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return createResponse(true, "User deleted successfully", null, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            return createResponse(false, e.getMessage(), null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return createResponse(false, "Failed to delete user", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
