package org.lordibe.folzgames.repositries;

import org.lordibe.folzgames.entities.Admin;
import org.lordibe.folzgames.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByEmailAndPassword(String email, String password);
    Optional<Admin> findFirstByEmail(String email);
}
