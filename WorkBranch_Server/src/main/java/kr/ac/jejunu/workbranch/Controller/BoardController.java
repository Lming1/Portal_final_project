package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.ApiResponseMessage;
import kr.ac.jejunu.workbranch.Model.Board;
import kr.ac.jejunu.workbranch.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ApiResponseMessage modify(@RequestBody Board board) {
//        boardRepository.existsById(board.getBid());
        if (boardRepository.existsByBid(board.getBid()) == true) {
            boardRepository.save(board);
            return new ApiResponseMessage(HttpStatus.OK, 200);
        } else {
            return new ApiResponseMessage(HttpStatus.BAD_REQUEST, 400);
        }
    }

    @DeleteMapping("/{bid}")
    public void delete(@PathVariable Integer bid) {
        boardRepository.delete(boardRepository.findById(bid).get());
    }




}
