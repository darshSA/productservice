package dev.darshana.productservice.repositories;

import dev.darshana.productservice.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    Product findProductByTitle(String title);
    Product findProductByTitleAndPrice_PriceOrderByCategory(String title, double price);
    List<Product> findAllByPrice_Currency(String currency);

    @Query(value = CustomQueries.FIND_ALL_BY_TITLE, nativeQuery = true)
    List<Product> findAllByTitle(String title);
    @Override
    List<Product> findAll();
    Optional<Product> findById(UUID uuid);
    @Override
    <S extends Product> S save(S entity);
    @Override
    void delete(Product entity);
    //Optional<Product> updateProductByUuid(UUID uuid, Product entity);

}
