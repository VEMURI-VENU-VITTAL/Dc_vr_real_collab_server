package com.example.controllers;

import com.example.enums.Status;
import com.example.models.User;
import com.example.responses.UserResponse;
import com.example.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/create")
    public UserResponse createUser(@RequestBody User user){
        try {
            user.setId(String.valueOf(UUID.randomUUID()));
            return userService.createUser(user);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new UserResponse(user, Status.FAILURE,e.getMessage());
        }
    }

    @GetMapping("/get")
    public UserResponse getUser(@RequestParam String userName, @RequestParam String password){
        User user = new User(userName, password);
        try{
            return userService.getUser(userName, password);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new UserResponse(user, Status.FAILURE,e.getMessage());
        }
    }
}
