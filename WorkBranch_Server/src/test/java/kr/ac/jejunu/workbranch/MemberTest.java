package kr.ac.jejunu.workbranch;


import kr.ac.jejunu.workbranch.Model.Member;
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
public class MemberTest {
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
        Member member = restTemplate.getForObject(PATH + "/" + id, Member.class);
        assertThat(member.getId(), is(id));
        assertThat(member.getEmail(), is(email));
        assertThat(member.getName(), is(name));
        assertThat(member.getPassword(), is(password));

    }

    @Test
    public void list() {
        List<Member> members = restTemplate.getForObject( PATH +"/list", List.class);
        assertThat(members, not(IsEmptyCollection.empty()));
    }

    @Test
    public void insert(){
        String email = "testuser4@test.com";
        String name = "minhyeok";
        String password = "12345";
        Member createdMember = createUser(email, name, password);
        validate(email, name, password, createdMember);
    }

    private void validate(String email, String name, String password, Member createdMember) {
        Member resultMember = restTemplate.getForObject(PATH + "/" + createdMember.getId(), Member.class);
        assertThat(resultMember.getEmail(), is(email));
        assertThat(resultMember.getName(), is(name));
        assertThat(resultMember.getPassword(), is(password));

    }

    private Member createUser(String email, String name, String password) {
        Member member = new Member();
        member.setEmail(email);
        member.setName(name);
        member.setPassword(password);
        return restTemplate.postForObject(PATH, member, Member.class);
    }

    @Test
    public void modify() {
        String email = "testuser3@test.com";
        String name = "minhyeok";
        String password = "12345";
        Member createdMember = createUser(email, name, password);
        createdMember.setName("tester1");
        createdMember.setPassword("12345678");
        restTemplate.put(PATH, createdMember);
        validate(email, "tester1", "12345678" , createdMember);
    }

    @Test
    public void delete() {
        String email = "testuser4@test.com";
        String name = "minhyeok";
        String password = "12345";
        Member createdMember = createUser(email, name, password);
        validate(email, name, password, createdMember);
        restTemplate.delete(PATH + "/" + createdMember.getId());
        Member member = restTemplate.getForObject(PATH + "/" + createdMember.getId(), Member.class);
        assertThat(member.getId(), is(nullValue()));
        assertThat(member.getEmail(), is(nullValue()));
        assertThat(member.getName(), is(nullValue()));
        assertThat(member.getPassword(), is(nullValue()));

    }
}
