package kr.ac.jejunu.workbranch.Controller;


import kr.ac.jejunu.workbranch.Model.*;
import kr.ac.jejunu.workbranch.Repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberRepository memberRepository;

    @PostMapping("/auth/login")
    public ApiResponseMessage login(@RequestBody AuthenticationRequest authenticationRequest, HttpSession session, HttpStatus status){
        try {
            String email = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(email, password);
            Authentication authentication = authenticationManager.authenticate(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
            Member member = memberRepository.findByEmail(email);
            log.info("========login=========");
//            return new ResponseEntity<>(member, status.OK);
            return new ApiResponseMessage(HttpStatus.OK, 200);
        }
        catch (Exception ex) {
//            return new ResponseEntity<Object>(HttpStatus.INTERNAL_SERVER_ERROR);
            return new ApiResponseMessage(HttpStatus.FORBIDDEN, 403, ex);
        }
    }

}
