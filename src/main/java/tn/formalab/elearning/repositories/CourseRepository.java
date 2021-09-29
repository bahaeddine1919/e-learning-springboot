package tn.formalab.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Lesson;
import tn.formalab.elearning.models.User;

import java.util.List;

public interface CourseRepository  extends JpaRepository<Course,Integer> {

    List<Course> findByNomtuteur(String nom);

    List<Lesson> findAllById(Integer id);
}
