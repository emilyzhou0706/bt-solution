package com.example.bt.api.solution.controller;

import com.example.bt.api.solution.entity.*;
import com.example.bt.api.solution.service.GetPostServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(value = PostController.class)
public class PostControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetPostServiceImpl getPostServiceImpl;

    @MockBean
    RestTemplate restTemplate;


    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersTestNormalCase() throws Exception {
        Post post1 =getPost1();
        Post post2 =getPost2();

        ArrayList arrayList=new ArrayList();
        arrayList.add(post1);
        arrayList.add(post2);


        ArrayList<Post> postList=new ArrayList();
        postList.add(post1);
        postList.add(post2);

        Mockito.when(
                getPostServiceImpl.getPosts()).thenReturn(postList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin/posts");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,result.getResponse().getStatus());

        Type type = new TypeToken<ArrayList<Post>>() {}.getType();
        Gson gson = new Gson();
        List<Post> posts = gson.fromJson(result.getResponse().getContentAsString(), type);
        assertListPosts(posts);
    }

    private void assertListPosts(List<Post> posts) {
        assertEquals(1, posts.get(0).getId());
        assertEquals(1, posts.get(0).getUserId());
        assertEquals("title1", posts.get(0).getTitle());
        assertEquals("body1", posts.get(0).getBody());

        assertEquals(2, posts.get(1).getId());
        assertEquals(2, posts.get(1).getUserId());
        assertEquals("title2", posts.get(1).getTitle());
        assertEquals("body2", posts.get(1).getBody());
    }

    private Post getPost1() {
        Post post1=new Post(1,1,"title1","body1");
        return post1;
    }
    private Post getPost2() {
        Post post2=new Post(2,2,"title2","body2");
        return post2;

    }
}
