package dev.darshana.productservice.repositories;

import dev.darshana.productservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Override
    Optional<Category> findById(UUID uuid);

    Optional<Category> findByNameIgnoreCase(String name);
}