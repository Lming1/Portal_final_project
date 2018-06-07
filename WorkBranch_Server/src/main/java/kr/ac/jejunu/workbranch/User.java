package kr.ac.jejunu.workbranch;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "user_info")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

}
