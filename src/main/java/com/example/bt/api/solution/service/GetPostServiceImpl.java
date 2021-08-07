package com.example.bt.api.solution.service;

import com.example.bt.api.solution.entity.Post;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("getPostServiceImpl")
public class GetPostServiceImpl implements GetPostService {

    @Autowired
    RestTemplate restTemplate;

    public GetPostServiceImpl() {
    }

    public GetPostServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<Post> getPosts() {

        Object[] objects=restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts", Object[].class);
        ObjectMapper mapper = new ObjectMapper();
        List<Post> posts=Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, Post.class))
                .collect(Collectors.toList());
        return posts;
    }
}
