package com.example.bt.api.solution.controller;

import com.example.bt.api.solution.entity.*;
import com.example.bt.api.solution.service.GetPostServiceImpl;
import com.example.bt.api.solution.service.GetUserServiceImpl;
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
@WebMvcTest(value = AdminController.class)
public class AdminControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GetUserServiceImpl getUserServiceImpl;

    @MockBean
    GetPostServiceImpl getPostServiceImpl;

    @MockBean
    RestTemplate restTemplate;


    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllUsersInfoTestNormalCase() throws Exception {
        User user1 = getUser1();
        User user2 = getUser2();

        ArrayList<User> usersList=new ArrayList();
        usersList.add(user1);
        usersList.add(user2);

        Post post1 =getPost11();
        Post post2 =getPost12();
        Post post3 =getPost21();
        Post post4 =getPost22();

        ArrayList postList=new ArrayList();
        postList.add(post1);
        postList.add(post2);
        postList.add(post3);
        postList.add(post4);

        Mockito.when(
                getUserServiceImpl.getUsers()).thenReturn(usersList);

        Mockito.when(
                getPostServiceImpl.getPosts()).thenReturn(postList);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/admin/usersInfo");

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,result.getResponse().getStatus());

        Type type = new TypeToken<ArrayList<UserInfo>>() {}.getType();
        Gson gson = new Gson();
        List<UserInfo> userInfoList = gson.fromJson(result.getResponse().getContentAsString(), type);
        System.out.println(userInfoList);
        assertListUsers(userInfoList);

    }

    private User getUser1() {
        User user1 = new User();
        user1.setId(1);
        user1.setName("nameTest");
        user1.setEmail("hello@gmail.com");
        Geo geo1 = new Geo();
        geo1.setLat(-37.3159);
        geo1.setLng(81.1496);
        Address address1 = new Address("abc street", "abc suite", "sydney", "2000", geo1);
        user1.setAddress(address1);
        Company company1 = new Company();
        company1.setBs("business bs");
        company1.setCatchPhrase("catchPhrase");
        company1.setName("company name");
        user1.setCompany(company1);
        user1.setUsername("testUserName");
        user1.setPhone("0123345556");
        user1.setWebsite("helloWord.org");
        return user1;
    }

    private User getUser2() {
        User user2 = new User();
        user2.setId(2);
        user2.setName("nameTest2");
        user2.setEmail("hello2@gmail.com");
        Geo geo2 = new Geo();
        geo2.setLat(-37.3159);
        geo2.setLng(81.1496);
        Address address2 = new Address("abc street2", "abc suite2", "sydney2", "2000", geo2);
        user2.setAddress(address2);
        Company company2 = new Company();
        company2.setBs("business bs2");
        company2.setCatchPhrase("catchPhrase2");
        company2.setName("company name2");
        user2.setCompany(company2);
        user2.setUsername("testUserName2");
        user2.setPhone("0123345556");
        user2.setWebsite("helloWord.org2");
        return user2;
    }

    private void assertListUsers(List<UserInfo> userInfo) {
        assertEquals("nameTest", userInfo.get(0).getUser().getName());
        assertEquals("testUserName", userInfo.get(0).getUser().getUsername());
        assertEquals("hello@gmail.com", userInfo.get(0).getUser().getEmail());
        assertEquals(1, userInfo.get(0).getUser().getId());
        assertEquals("0123345556", userInfo.get(0).getUser().getPhone());
        assertEquals("helloWord.org", userInfo.get(0).getUser().getWebsite());

        assertEquals("sydney", userInfo.get(0).getUser().getAddress().getCity());
        assertEquals("2000", userInfo.get(0).getUser().getAddress().getZipcode());
        assertEquals("abc suite", userInfo.get(0).getUser().getAddress().getSuite());
        assertEquals("abc street", userInfo.get(0).getUser().getAddress().getStreet());

        assertEquals(-37.3159, userInfo.get(0).getUser().getAddress().getGeo().getLat());
        assertEquals(81.1496, userInfo.get(0).getUser().getAddress().getGeo().getLng());

        assertEquals(1, userInfo.get(0).getPostLists().get(0).getUserId());
        assertEquals(1, userInfo.get(0).getPostLists().get(0).getId());
        assertEquals("user1post1title", userInfo.get(0).getPostLists().get(0).getTitle());
        assertEquals("user1post1body", userInfo.get(0).getPostLists().get(0).getBody());

        assertEquals(1, userInfo.get(0).getPostLists().get(1).getUserId());
        assertEquals(2, userInfo.get(0).getPostLists().get(1).getId());
        assertEquals("user1post2title", userInfo.get(0).getPostLists().get(1).getTitle());
        assertEquals("user1post2body", userInfo.get(0).getPostLists().get(1).getBody());


        assertEquals("nameTest2", userInfo.get(1).getUser().getName());
        assertEquals("testUserName2", userInfo.get(1).getUser().getUsername());
        assertEquals("hello2@gmail.com", userInfo.get(1).getUser().getEmail());
        assertEquals(2, userInfo.get(1).getUser().getId());
        assertEquals("0123345556", userInfo.get(1).getUser().getPhone());
        assertEquals("helloWord.org2", userInfo.get(1).getUser().getWebsite());

        assertEquals("sydney2", userInfo.get(1).getUser().getAddress().getCity());
        assertEquals("2000", userInfo.get(1).getUser().getAddress().getZipcode());
        assertEquals("abc suite2", userInfo.get(1).getUser().getAddress().getSuite());
        assertEquals("abc street2", userInfo.get(1).getUser().getAddress().getStreet());

        assertEquals(-37.3159, userInfo.get(1).getUser().getAddress().getGeo().getLat());
        assertEquals(81.1496, userInfo.get(1).getUser().getAddress().getGeo().getLng());

        assertEquals(2, userInfo.get(1).getPostLists().get(0).getUserId());
        assertEquals(1, userInfo.get(1).getPostLists().get(0).getId());
        assertEquals("user2post1title", userInfo.get(1).getPostLists().get(0).getTitle());
        assertEquals("user2post1body", userInfo.get(1).getPostLists().get(0).getBody());

        assertEquals(2, userInfo.get(1).getPostLists().get(1).getUserId());
        assertEquals(2, userInfo.get(1).getPostLists().get(1).getId());
        assertEquals("user2post2title", userInfo.get(1).getPostLists().get(1).getTitle());
        assertEquals("user2post2body", userInfo.get(1).getPostLists().get(1).getBody());

    }

    private Post getPost11() {
        Post post1=new Post(1,1,"user1post1title","user1post1body");
        return post1;
    }

    private Post getPost12() {
        Post post1=new Post(1,2,"user1post2title","user1post2body");
        return post1;
    }

    private Post getPost21() {
        Post post2=new Post(2,1,"user2post1title","user2post1body");
        return post2;
    }

    private Post getPost22() {
        Post post2=new Post(2,2,"user2post2title","user2post2body");
        return post2;
    }
}
