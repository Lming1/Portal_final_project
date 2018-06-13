package kr.ac.jejunu.workbranch.Model;

import lombok.Data;

import java.util.List;

@Data
public class AuthenticationToken {
    private String email;
    private List<MemberRole> roles;
    private String token;


    public AuthenticationToken(String email, List<MemberRole> roles, String token) {
        this.email = email;
        this.roles = roles;
        this.token = token;
    }
}
