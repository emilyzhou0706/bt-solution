package com.example.bt.api.solution.service;

import com.example.bt.api.solution.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class GetPostServiceImplTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private GetPostServiceImpl getPostServiceImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnPost_whenGetPostIsCalled(){
        Post post1 =getPost1();
        Post post2 =getPost2();

        Object[] objects= new Object[2];
        objects[0]=post1;
        objects[1]=post2;

        doReturn(objects).when(restTemplate).getForObject("https://jsonplaceholder.typicode.com/posts", Object[].class);

        List<Post> posts=getPostServiceImpl.getPosts();

        assertListPosts(posts);

        System.out.println(posts);
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
