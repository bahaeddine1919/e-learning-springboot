package tn.formalab.elearning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "enrolled")
public class Enrolled {


    @Id
    @GeneratedValue
    public  Integer id;


    @ManyToOne
    @JsonIgnoreProperties("user")
    @JoinColumn(name ="user_id")
    public User user;


    @ManyToOne
    @JoinColumn(name ="course_id" )
    public Course course;


    @Column(name = "date_enrolled")
    public LocalDate date_enrolled=LocalDate.now();



}
