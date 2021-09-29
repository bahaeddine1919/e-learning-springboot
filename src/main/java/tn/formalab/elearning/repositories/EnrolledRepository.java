package tn.formalab.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearning.models.Course;
import tn.formalab.elearning.models.Enrolled;
import tn.formalab.elearning.models.User;

import java.util.List;

public interface EnrolledRepository extends JpaRepository<Enrolled,Integer> {
    List<Enrolled> findByUser(User user);
    List<Enrolled> findByUserAndCourse(User user, Course course);

}
