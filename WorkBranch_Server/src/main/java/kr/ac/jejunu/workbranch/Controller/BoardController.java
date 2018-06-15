package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.ApiResponseMessage;
import kr.ac.jejunu.workbranch.Model.Board;
import kr.ac.jejunu.workbranch.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping("/{bid}")
    public Board get(@PathVariable Integer bid) {
        return boardRepository.findById(bid).get();
    }

    @GetMapping("/list/{projectId}")
    public List<Board> getPid(@PathVariable Integer projectId) {

        return boardRepository.findAllByProjectId(projectId);
    }

    @PostMapping
    public ApiResponseMessage create(@RequestBody Board board) {
        boardRepository.save(board);
        return new ApiResponseMessage(HttpStatus.OK, 200);
    }

    @GetMapping("/list")
    public List<Board> list() {
        return boardRepository.findAll();
    }

    @PutMapping
    public void modify(@RequestBody Board board) {
        boardRepository.save(board);
    }

    @DeleteMapping("/{bid}")
    public void delete(@PathVariable Integer bid) {
        boardRepository.delete(boardRepository.findById(bid).get());
    }




}
