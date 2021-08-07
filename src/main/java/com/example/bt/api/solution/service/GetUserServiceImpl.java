package com.example.bt.api.solution.service;

import com.example.bt.api.solution.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service("getPersonServiceImpl")
public class GetUserServiceImpl implements GetUserService {

    @Autowired
    RestTemplate restTemplate;

    public GetUserServiceImpl() {
    }

    public GetUserServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<User> getUsers() {

        Object[] objects=restTemplate.getForObject("https://jsonplaceholder.typicode.com/users", Object[].class);
        ObjectMapper mapper = new ObjectMapper();
        List<User> users=Arrays.stream(objects)
                .map(object -> mapper.convertValue(object, User.class))
                .collect(Collectors.toList());
        return users;
    }
}
