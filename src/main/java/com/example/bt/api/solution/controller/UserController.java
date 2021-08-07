package com.example.bt.api.solution.controller;

import com.example.bt.api.solution.entity.User;
import com.example.bt.api.solution.service.GetUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private GetUserServiceImpl getUserServiceImpl;

    @GetMapping(path="/users")
    public @ResponseBody List<User> getAllUsers() {
        return getUserServiceImpl.getUsers();
    }

}