package tn.formalab.elearning.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "description", nullable = false)
    public String description;

    @Column(name = "imageUrl", nullable = false)
    public String imageUrl;
    @Column(name = "videoUrl", nullable = false)
    public String videoUrl;

    @Column(name = "duration ", nullable = false)
    public String duration;

    @ManyToOne
    @JsonIgnoreProperties("lessons")
    @JoinColumn(name = "idcourse")
    public Course course;


}
