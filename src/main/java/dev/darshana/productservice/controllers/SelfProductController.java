package dev.darshana.productservice.controllers;

import dev.darshana.productservice.dtos.ProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;
import dev.darshana.productservice.services.SelfProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/selfProducts")
public class SelfProductController {
    private SelfProductService selfProductService;

    public SelfProductController(SelfProductService selfProductService){
        this.selfProductService = selfProductService;
    }

    @GetMapping
    public List<ProductDto> getAllProducts(){
        return selfProductService.getAllProducts();
    }

    @GetMapping("{id}")
    public ProductDto getProductById(@PathVariable("id") String id) throws NotFoundException {
        return selfProductService.getProductById(id);
    }

    @PostMapping
    public ProductDto createProduct(@RequestBody ProductDto productDto){
        return selfProductService.createProduct(productDto);
    }

    @DeleteMapping("{id}")
    public String deleteProductById(@PathVariable("id") String id) throws NotFoundException{
        return selfProductService.deleteProduct(id);
    }

    @PutMapping("{id}")
    public ProductDto updateProductById(@PathVariable("id") String id,
                                        @RequestBody ProductDto productDto) throws NotFoundException {
        return selfProductService.updateProductById(productDto, id);
    }
}
