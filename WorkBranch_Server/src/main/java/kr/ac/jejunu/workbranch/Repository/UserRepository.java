package kr.ac.jejunu.workbranch.Repository;


import kr.ac.jejunu.workbranch.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
