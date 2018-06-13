package kr.ac.jejunu.workbranch.Model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@EqualsAndHashCode(of = "email")
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String user_photo;

    @Column(name = "reg_date")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

//    @OneToMany
//    @JoinColumn(name = "user_email")
//    private List<Project> projects;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="email")
    private List<MemberRole> roles;

}
