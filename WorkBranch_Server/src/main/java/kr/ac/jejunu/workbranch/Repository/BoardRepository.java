package kr.ac.jejunu.workbranch.Repository;

import kr.ac.jejunu.workbranch.Model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    List<Board> findAllByProjectId(Integer projectId);

    Board findByProjectId(Integer projectId);
    Boolean existsByBid(Integer bid);

}
