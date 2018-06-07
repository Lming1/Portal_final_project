package kr.ac.jejunu.workbranch;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest {
    public static final String PATH = "/api/user";

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void get(){
        Integer id = 1;
        String email = "testuser@test.com";
        String name = "ming";
        String password = "1234";
        User user = restTemplate.getForObject(PATH + "/" + id, User.class);
        assertThat(user.getId(), is(id));
        assertThat(user.getEmail(), is(email));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void insert(){
        
    }
}
