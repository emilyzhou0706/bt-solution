package com.example.bt.api.solution.service;

import com.example.bt.api.solution.entity.Address;
import com.example.bt.api.solution.entity.Company;
import com.example.bt.api.solution.entity.Geo;
import com.example.bt.api.solution.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

public class GetUserServiceImplTest {
    @Mock
    RestTemplate restTemplate;

    @InjectMocks
    private GetUserServiceImpl getUserServiceImpl;

    @BeforeEach
    public void setupMock() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnUser_whenGetUserIsCalled(){
        User user1 = getUser1();
        User user2 = getUser2();

        Object[] objects= new Object[2];
        objects[0]=user1;
        objects[1]=user2;

        doReturn(objects).when(restTemplate).getForObject("https://jsonplaceholder.typicode.com/users", Object[].class);

        List<User> users=getUserServiceImpl.getUsers();

        assertListUsers(users);

        System.out.println(users);
    }

    private void assertListUsers(List<User> users) {
        assertEquals("nameTest", users.get(0).getName());
        assertEquals("testUserName", users.get(0).getUsername());
        assertEquals("hello@gmail.com", users.get(0).getEmail());
        assertEquals(1, users.get(0).getId());
        assertEquals("0123345556", users.get(0).getPhone());
        assertEquals("helloWord.org", users.get(0).getWebsite());

        assertEquals("sydney", users.get(0).getAddress().getCity());
        assertEquals("2000", users.get(0).getAddress().getZipcode());
        assertEquals("abc suite", users.get(0).getAddress().getSuite());
        assertEquals("abc street", users.get(0).getAddress().getStreet());

        assertEquals(-37.3159, users.get(0).getAddress().getGeo().getLat());
        assertEquals(81.1496, users.get(0).getAddress().getGeo().getLng());


        assertEquals("nameTest2", users.get(1).getName());
        assertEquals("testUserName2", users.get(1).getUsername());
        assertEquals("hello2@gmail.com", users.get(1).getEmail());
        assertEquals(2, users.get(1).getId());
        assertEquals("0123345556", users.get(1).getPhone());
        assertEquals("helloWord.org2", users.get(1).getWebsite());

        assertEquals("sydney2", users.get(1).getAddress().getCity());
        assertEquals("2000", users.get(1).getAddress().getZipcode());
        assertEquals("abc suite2", users.get(1).getAddress().getSuite());
        assertEquals("abc street2", users.get(1).getAddress().getStreet());

        assertEquals(-37.3159, users.get(1).getAddress().getGeo().getLat());
        assertEquals(81.1496, users.get(1).getAddress().getGeo().getLng());
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


}
