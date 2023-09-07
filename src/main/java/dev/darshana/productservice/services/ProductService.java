package dev.darshana.productservice.services;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.models.Product;

public interface ProductService {
    GenericProductDto getProductById(Long id);
    GenericProductDto createProduct(GenericProductDto product);
}
