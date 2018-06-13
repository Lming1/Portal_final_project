package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.Member;
import kr.ac.jejunu.workbranch.Model.MemberRole;
import kr.ac.jejunu.workbranch.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final MemberRepository memberRepository;

    @GetMapping("/{id}")
    public Member get(@PathVariable Integer id) {
        return memberRepository.findById(id).get();
    }

    @GetMapping("/list")
    public List<Member> list() {
        return memberRepository.findAll();
    }

    @GetMapping("/test/{email}")
    public Member getEmail(@PathVariable String email){
        return memberRepository.findByEmail(email);
    }

    @PostMapping
    public Member insert(@RequestBody Member member) {
        MemberRole role = new MemberRole();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        role.setRoleName("USER");
        member.setRoles(Arrays.asList(role));
        return memberRepository.save(member);
    }

    @PutMapping
    public void modify(@RequestBody Member member) {
        memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberRepository.delete(memberRepository.findById(id).get());
    }
}
