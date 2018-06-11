package kr.ac.jejunu.workbranch;


import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity(name = "project")
@ToString
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;

    @Column(nullable = false, length = 255 , name = "project_name")
    private String projectName;


    @Column(name = "project_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pDate;

    @Column(name = "user_email")
    private String userEmail;


}
