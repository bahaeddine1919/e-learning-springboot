package tn.formalab.elearning.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "courses")
public class Course {

    @Id
    @GeneratedValue
    public  Integer id;

    @Column(name = "name",nullable = false)
    public  String name;

    @Column(name = "description",nullable = false)
    public  String description;

    @Column(name = "imageUrl",nullable = false)
    public  String imageUrl;

    @Column(name = "price",nullable = false)
    public  Double price;
    @Column(name = "Course_Forum",nullable = false)
    public  String Course_Forum;
    @Column(name = "nomtuteur",nullable = false)
    public  String nomtuteur;

    @OneToMany(mappedBy ="course")
    @JsonIgnoreProperties("course")
    public List<Lesson> lessons;

    @ManyToOne
    @JoinColumn(name = "idtuteur")
    public User tuteur;
}
