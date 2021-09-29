package tn.formalab.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Lesson;

import java.util.List;

public interface LessonRepository extends JpaRepository<Lesson,Integer> {

}
