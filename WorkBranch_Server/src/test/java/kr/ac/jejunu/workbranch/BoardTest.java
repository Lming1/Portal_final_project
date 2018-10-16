package kr.ac.jejunu.workbranch;


import kr.ac.jejunu.workbranch.Model.Board;
import kr.ac.jejunu.workbranch.Model.Project;
import kr.ac.jejunu.workbranch.Repository.BoardRepository;
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
import java.util.Random;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardTest {
    public static final String PATH = "/api/board";
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BoardRepository boardRepository;


    @Test
    public void get() {
        Integer bid = 1;
        String title = "board_test";
        String contents = "test_contents";
        String email = "testuser@test.com";
        Integer project_id = 1;
        Board board = restTemplate.getForObject(PATH + "/" + bid, Board.class);
        assertThat(board.getBid(), is(bid));
        assertThat(board.getTitle(), is(title));
        assertThat(board.getContents(), is(contents));
        assertThat(board.getEmail(), is(email));
        assertThat(board.getProjectId(), is(project_id));
    }

    @Test
    public void create() throws ParseException {
        String title = "board_test";
        String contents = "test_contents";
        String email = "testuser@test.com";
        Integer project_id = 1;
        Board createdBoard = createBoard(title, contents, email, project_id);
        validate(title, contents, email, createdBoard);
    }

    private void validate(String title, String contents, String email, Board createdBoard) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        Board resultBoard = restTemplate.getForObject(PATH + "/" + createdBoard.getBid(), Board.class);
        assertThat(resultBoard.getTitle(), is(title));
        assertThat(resultBoard.getContents(), is(contents));
        assertThat(resultBoard.getEmail(), is(email));
        assertThat(resultBoard.getBDate(), is(date));
    }

    private Board createBoard(String title, String contents, String email, Integer project_id) throws ParseException {
        Board board = new Board();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        date = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
        board.setTitle(title);
        board.setContents(contents);
        board.setEmail(email);
        board.setProjectId(project_id);
        board.setBDate(date);
        return restTemplate.postForObject(PATH, board, Board.class);
    }

    @Test
    public void list() {
        List<Board> boards = restTemplate.getForObject( PATH +"/list", List.class);
        assertThat(boards, not(IsEmptyCollection.empty()));
    }

    @Test
    public void modify() throws ParseException {
        String title = "board_title1";
        String contents = "test_contents";
        String email = "testuser@test.com";
        Integer project_id = 1;
        Integer bid = 20;
        Board createdBoard = createBoard(title, contents, email, project_id);
        createdBoard.setTitle("title1");
        createdBoard.setContents("ggggggg");
        restTemplate.put(PATH, createdBoard);
        validate("title1", "ggggggg", email, createdBoard);
//        Board createdBoard = createBoard(title, contents, email, project_id);
//        createdBoard.setTitle("title1");
//        createdBoard.setContents("ggggggg");
//        restTemplate.put(PATH, createdBoard);


    }

    @Test
    public void delete() throws ParseException {
        String title = "board_title";
        String contents = "test_contents";
        String email = "testuser@test.com";
        Integer project_id = 1;
        Board createdBoard = createBoard(title, contents, email, project_id);
        validate(title, contents, email, createdBoard);
        restTemplate.delete(PATH + "/" + createdBoard.getBid());
        Board board = restTemplate.getForObject(PATH + "/" + createdBoard.getBid(), Board.class);
        assertThat(board.getBid(), is(nullValue()));
        assertThat(board.getTitle(), is(nullValue()));
        assertThat(board.getContents(), is(nullValue()));
        assertThat(board.getEmail(), is(nullValue()));
        assertThat(board.getProjectId(), is(nullValue()));
        assertThat(board.getBDate(), is(nullValue()));
    }
}
