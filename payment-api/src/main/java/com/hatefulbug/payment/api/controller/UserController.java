package com.hatefulbug.payment.api.controller;

import com.hatefulbug.payment.api.model.User;
import com.hatefulbug.payment.api.reponse.ApiResponse;
import com.hatefulbug.payment.api.request.PartialUser;
import com.hatefulbug.payment.api.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable(value = "userId") int userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(new ApiResponse(user, "User successfully obtained"), HttpStatus.OK);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ApiResponse> getUserByEmail(@PathVariable(value = "email") String email) {
        User user = userService.getUserByEmail(email);
        return new ResponseEntity<>(new ApiResponse(user, "User successfully obtained"), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody PartialUser user) {
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(new ApiResponse(createdUser, "User successfully created"), HttpStatus.CREATED);
    }

    @PutMapping("update")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody PartialUser user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(new ApiResponse(updatedUser, "User successfully updated"), HttpStatus.OK);
    }

    @DeleteMapping("delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "userId") int userId) {
        User deletedUser = userService.deleteUser(userId);
        return new ResponseEntity<>(new ApiResponse(deletedUser, "User successfully deleted"), HttpStatus.OK);
    }

}
