package com.example.bt.api.solution.controller;

import com.example.bt.api.solution.entity.Post;
import com.example.bt.api.solution.service.GetPostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class PostController {

    @Autowired
    private GetPostServiceImpl getPostServiceImpl;

    @GetMapping(path="/posts")
    public @ResponseBody List<Post> getAllPosts() {
        return getPostServiceImpl.getPosts();
    }
}