package dev.darshana.productservice.repositories;

import dev.darshana.productservice.models.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PriceRepository extends JpaRepository<Price, UUID> {
    @Override
    <S extends Price> S save(S entity);
}
