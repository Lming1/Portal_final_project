package kr.ac.jejunu.workbranch.Repository;

import kr.ac.jejunu.workbranch.Model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
}
