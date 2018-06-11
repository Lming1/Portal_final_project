package kr.ac.jejunu.workbranch;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Project insert(@RequestBody Project project) {
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
}
