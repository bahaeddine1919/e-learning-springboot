package tn.formalab.elearning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.formalab.elearning.models.User;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findByEmail(String email);

    List<User> findByRole(String role);
}
