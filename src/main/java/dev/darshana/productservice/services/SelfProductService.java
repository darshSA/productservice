package dev.darshana.productservice.services;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.models.Product;
import org.springframework.stereotype.Service;

@Service("selfProductServiceImpl")
public class SelfProductService implements ProductService{
    @Override
    public GenericProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public GenericProductDto createProduct(GenericProductDto product) {
        return null;
    }
}
