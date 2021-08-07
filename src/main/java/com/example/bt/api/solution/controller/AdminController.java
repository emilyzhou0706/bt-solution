package com.example.bt.api.solution.controller;

import com.example.bt.api.solution.entity.Post;
import com.example.bt.api.solution.entity.User;
import com.example.bt.api.solution.entity.UserInfo;
import com.example.bt.api.solution.service.GetPostServiceImpl;
import com.example.bt.api.solution.service.GetUserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private GetPostServiceImpl getPostServiceImpl;

    @Autowired
    private GetUserServiceImpl getUserServiceImpl;


    @GetMapping(path="/usersInfo")
    public @ResponseBody List<UserInfo> getAllUserInfo() {
        List<Post> posts=getPostServiceImpl.getPosts();
        List<User> users=getUserServiceImpl.getUsers();

        List<UserInfo> userInfoList
                =users.stream().map(
                user -> {
                    return new UserInfo(user,posts.stream().filter(
                            post -> Integer.valueOf(post.getUserId()).equals(user.getId())
                    ).collect(Collectors.toList()));
                }
        ).collect(Collectors.toList());
        return userInfoList;

    }
}