package kr.ac.jejunu.workbranch.Repository;

import kr.ac.jejunu.workbranch.Model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
}
