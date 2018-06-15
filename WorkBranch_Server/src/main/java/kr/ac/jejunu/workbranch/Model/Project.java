package kr.ac.jejunu.workbranch.Model;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;



    @Column(nullable = false, length = 255 , name = "project_name")
    private String projectName;

    @Column(nullable = false, length = 255 , name = "p_email")
    private String p_email;

    @Column(name = "project_date", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date pDate;


//    @ManyToOne
//    @JoinColumn(foreignKey = @ForeignKey(name = "fk_project_emails"))
//    private List<Member> emails;



}
