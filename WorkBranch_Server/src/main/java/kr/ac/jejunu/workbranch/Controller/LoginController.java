package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.AuthenticationRequest;
import kr.ac.jejunu.workbranch.Model.AuthenticationToken;
import kr.ac.jejunu.workbranch.Model.Member;
import kr.ac.jejunu.workbranch.Model.ResultStatus;
import kr.ac.jejunu.workbranch.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/auth/login")
    public ResultStatus login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session){
        try {
            String email = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            Member member = memberRepository.findByEmail(email);
            log.info("========login=========");
            return new ResultStatus(200);
        } catch (Exception e) {
            return new ResultStatus(500);
        }
    }

}
