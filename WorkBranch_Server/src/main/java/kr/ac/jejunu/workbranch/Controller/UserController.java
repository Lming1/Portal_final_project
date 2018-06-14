package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.ApiResponseMessage;
import kr.ac.jejunu.workbranch.Model.Member;
import kr.ac.jejunu.workbranch.Model.MemberRole;
import kr.ac.jejunu.workbranch.Repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ApiResponseMessage insert(@RequestBody Member member, HttpServletRequest req, HttpServletResponse res, MultipartFile user_photo) throws IOException {
        if (member.getEmail() == "" || member.getName() == "") {
            return new ApiResponseMessage(HttpStatus.BAD_REQUEST, 400);
        }
        try {
            MemberRole role = new MemberRole();
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            member.setPassword(passwordEncoder.encode(member.getPassword()));
            role.setRoleName("USER");
            member.setRoles(Arrays.asList(role));

            memberRepository.save(member);

            return new ApiResponseMessage(HttpStatus.OK, 200);
        } catch (Exception ex){
            return new ApiResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR, 500, ex);
        }

    }

    @PutMapping("/{email}")
    public void modify(@RequestBody Member member, @PathVariable String email) {


        memberRepository.save(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        memberRepository.delete(memberRepository.findById(id).get());
    }
}
