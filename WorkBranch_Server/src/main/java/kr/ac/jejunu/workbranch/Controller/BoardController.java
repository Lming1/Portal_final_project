package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.Board;
import kr.ac.jejunu.workbranch.Repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardController {
    private final BoardRepository boardRepository;

    @GetMapping("/{bid}")
    public Board get(@PathVariable Integer bid) {
        return boardRepository.findById(bid).get();
    }

    @PostMapping
    public Board create(@RequestBody Board board) {
        return boardRepository.save(board);
    }



}
