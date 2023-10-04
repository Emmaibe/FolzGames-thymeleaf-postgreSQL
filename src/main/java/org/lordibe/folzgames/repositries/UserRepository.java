package org.lordibe.folzgames.repositries;

import org.lordibe.folzgames.entities.Category;
import org.lordibe.folzgames.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findFirstByEmail(String email);
    User findUserByEmail(String email);
}
