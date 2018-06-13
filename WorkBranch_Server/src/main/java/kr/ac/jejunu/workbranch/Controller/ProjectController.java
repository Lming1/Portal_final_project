package kr.ac.jejunu.workbranch.Controller;

import kr.ac.jejunu.workbranch.Model.Project;
import kr.ac.jejunu.workbranch.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final ProjectRepository projectRepository;
    @GetMapping("/{pid}")
    public Project get(@PathVariable Integer pid) {
        return projectRepository.findById(pid).get();
    }

    @PostMapping
    public Project create(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @PutMapping
    public void modify(@RequestBody Project project) {
        projectRepository.save(project);
    }

    @DeleteMapping("/{pid}")
    public void delete(@PathVariable Integer pid) {
        projectRepository.delete(projectRepository.findById(pid).get());
    }

    @GetMapping("/list")
    public List<Project> list() {
        return projectRepository.findAll();
    }
}
