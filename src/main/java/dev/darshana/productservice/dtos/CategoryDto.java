package dev.darshana.productservice.dtos;

import dev.darshana.productservice.models.Product;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDto {
    private String id;
    private String name;
    private List<Product> products;
}
