package dev.darshana.productservice.services;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;

import java.util.List;

public interface ProductService {
    GenericProductDto getProductById(Long id) throws NotFoundException;
    GenericProductDto createProduct(GenericProductDto product);
    List<GenericProductDto> getAllProducts();
    GenericProductDto deleteProduct(Long id);
    GenericProductDto updateProductById(GenericProductDto product, Long id);
}
