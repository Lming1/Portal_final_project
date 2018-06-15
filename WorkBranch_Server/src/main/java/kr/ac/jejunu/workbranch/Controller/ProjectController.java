package kr.ac.jejunu.workbranch.Controller;

import kr.ac.jejunu.workbranch.Model.ApiResponseMessage;
import kr.ac.jejunu.workbranch.Model.Member;
import kr.ac.jejunu.workbranch.Model.Project;
import kr.ac.jejunu.workbranch.Repository.MemberRepository;
import kr.ac.jejunu.workbranch.Repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project")
@RequiredArgsConstructor
public class ProjectController {
    private final MemberRepository memberRepository;
    private final ProjectRepository projectRepository;
    @GetMapping("/{pid}")
    public Project get(@PathVariable Integer pid) {
        return projectRepository.findById(pid).get();
    }

    @PostMapping
    public ApiResponseMessage create(@RequestBody Project project) {
        try {
            projectRepository.save(project);
            return new ApiResponseMessage(HttpStatus.OK, 200);
        } catch (Exception ex){
            return new ApiResponseMessage(HttpStatus.BAD_REQUEST, 400);
        }


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
