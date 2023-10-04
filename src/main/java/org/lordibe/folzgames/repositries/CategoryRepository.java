package org.lordibe.folzgames.repositries;

import org.lordibe.folzgames.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
