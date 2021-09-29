package tn.formalab.elearning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "firstname", nullable = false)
    public String firstname;

    @Column(name = "lastname", nullable = false)
    public String lastname;

    @Column(name = "email", unique = true, nullable = false)
    public String email;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "role")
    public String role = "client";

    @Column(name = "accountState")
    public Boolean accountState = true;

    @Column(name = "date_star")
    public String date_star=LocalDate.now().toString();

    @Column(name = "revenu_total", nullable = true)
    public double revenu_total;

    @Column(name = "sold", nullable = true)
    public double sold;

    @Column(name = "office", nullable = true)
    public String office	;

    @Column(name = "imageUrl", nullable = true)
    public  String imageUrl;

    @Column(name = "description", nullable = true)
    public String description;

    @OneToMany(mappedBy ="user")
    @JsonIgnoreProperties("user")
    public List<Enrolled> enrolleds;


    @OneToMany(mappedBy ="tuteur")
    @JsonIgnoreProperties("tuteur")
    public List<Course> courses;



}
