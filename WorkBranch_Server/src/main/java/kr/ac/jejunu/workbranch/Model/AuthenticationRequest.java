package kr.ac.jejunu.workbranch.Model;


import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
