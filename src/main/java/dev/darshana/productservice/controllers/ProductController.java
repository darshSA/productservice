package dev.darshana.productservice.controllers;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("fakeStoreProductServiceImpl") ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public List<GenericProductDto> getAllProducts(){
        return List.of(
                new GenericProductDto();
                new GenericProductDto();
        );
    }

    @GetMapping("{id}")
    public GenericProductDto getProductById(@PathVariable("id") Long id){
        return productService.getProductById(id);
        //return "Here is product id: "+id;
    }

    @DeleteMapping("{id}")
    public void deleteProductById(){

    }

    @PostMapping
    public GenericProductDto createProduct(@RequestBody GenericProductDto product){
        return productService.createProduct(product);
    }

    @PutMapping("{id}")
    public void updateProductById(){

    }
}
