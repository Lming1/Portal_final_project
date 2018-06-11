package kr.ac.jejunu.workbranch;


import kr.ac.jejunu.workbranch.Model.User;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
        String user_photo = null;
        User user = restTemplate.getForObject(PATH + "/" + id, User.class);
        assertThat(user.getId(), is(id));
        assertThat(user.getEmail(), is(email));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void list() {
        List<User> users = restTemplate.getForObject( PATH +"/list", List.class);
        assertThat(users, not(IsEmptyCollection.empty()));
    }

    @Test
    public void insert(){
        String email = "testuser4@test.com";
        String name = "minhyeok";
        String password = "12345";
        User createdUser = createUser(email, name, password);
        validate(email, name, password, createdUser);
    }

    private void validate(String email, String name, String password, User createdUser) {
        User resultUser = restTemplate.getForObject(PATH + "/" + createdUser.getId(), User.class);
        assertThat(resultUser.getEmail(), is(email));
        assertThat(resultUser.getName(), is(name));
        assertThat(resultUser.getPassword(), is(password));

    }

    private User createUser(String email, String name, String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(password);
        return restTemplate.postForObject(PATH, user, User.class);
    }

    @Test
    public void modify() {
        String email = "testuser3@test.com";
        String name = "minhyeok";
        String password = "12345";
        User createdUser = createUser(email, name, password);
        createdUser.setName("tester1");
        createdUser.setPassword("12345678");
        restTemplate.put(PATH, createdUser);
        validate(email, "tester1", "12345678" , createdUser);
    }

    @Test
    public void delete() {
        String email = "testuser4@test.com";
        String name = "minhyeok";
        String password = "12345";
        User createdUser = createUser(email, name, password);
        validate(email, name, password, createdUser);
        restTemplate.delete(PATH + "/" + createdUser.getId());
        User user = restTemplate.getForObject(PATH + "/" + createdUser.getId(), User.class);
        assertThat(user.getId(), is(nullValue()));
        assertThat(user.getEmail(), is(nullValue()));
        assertThat(user.getName(), is(nullValue()));
        assertThat(user.getPassword(), is(nullValue()));

    }
}
