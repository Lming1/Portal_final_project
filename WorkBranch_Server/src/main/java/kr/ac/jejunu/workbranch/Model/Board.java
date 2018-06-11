package kr.ac.jejunu.workbranch.Model;


import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity(name = "board")
@ToString
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;

    @Column(name = "title")
    private String title;
    @Column(name = "contents")
    private String contents;

    @Column(name = "email")
    private String email;

    @Column(name = "project_id")
    private Integer project_id;

    @Column(name = "b_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bDate;

}
