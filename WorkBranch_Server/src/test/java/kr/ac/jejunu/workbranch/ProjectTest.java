package kr.ac.jejunu.workbranch;


import kr.ac.jejunu.workbranch.Model.Project;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProjectTest {
    public static final String PATH = "/api/project";
    @Autowired
    private TestRestTemplate restTemplate;



    @Test
    public void get() {
        Integer pid = 1;
        String project_name = "test";
        String email = "testuser1@test.com";
        Project project = restTemplate.getForObject(PATH + "/" + pid, Project.class);

        assertThat(project.getPid(), is(pid));
        assertThat(project.getProjectName(), is(project_name));
        assertThat(project.getUserEmail(), is(email));
    }

    @Test
    public void create() throws ParseException {

//        String
        String userEmail = "testuser1@test.com";
        String project_name = "test1";
        Project createdProject = createProject(userEmail, project_name);
        validate(userEmail, project_name, createdProject);
    }

    private void validate(String userEmail, String project_name, Project createdProject) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Project resultProject = restTemplate.getForObject(PATH + "/" + createdProject.getPid(), Project.class);
        assertThat(resultProject.getUserEmail(), is(userEmail));
        assertThat(resultProject.getProjectName(), is(project_name));
        assertThat(resultProject.getPDate(), is(date));
    }

    private Project createProject(String email, String project_name) throws ParseException {
        Project project = new Project();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        project.setUserEmail(email);
        project.setProjectName(project_name);
        project.setPDate(date);
        return restTemplate.postForObject(PATH, project, Project.class);
    }

    @Test
    public void modify() throws ParseException {
        String userEmail = "testuser1@test.com";
        String project_name = "test1";
        Project createdProject = createProject(userEmail, project_name);
        createdProject.setProjectName("test123");
        restTemplate.put(PATH, createdProject);
        validate(userEmail, "test123", createdProject);
    }

    @Test
    public void delete() throws ParseException {
        String userEmail = "testuser1@test.com";
        String project_name = "test1";
        Project createdProject = createProject(userEmail, project_name);
        validate(userEmail, project_name, createdProject);
        restTemplate.delete(PATH + "/" + createdProject.getPid());
        Project project = restTemplate.getForObject(PATH + "/" + createdProject.getPid(), Project.class);
        assertThat(project.getPid(), is(nullValue()));
        assertThat(project.getProjectName(), is(nullValue()));
        assertThat(project.getUserEmail(), is(nullValue()));
        assertThat(project.getPDate(), is(nullValue()));

    }

    @Test
    public void list() {
        List<Project> projects = restTemplate.getForObject( PATH +"/list", List.class);
        assertThat(projects, not(IsEmptyCollection.empty()));
    }

}
