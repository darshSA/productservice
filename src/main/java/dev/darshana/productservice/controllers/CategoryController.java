package dev.darshana.productservice.controllers;

import dev.darshana.productservice.dtos.CategoryDto;
import dev.darshana.productservice.dtos.ProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;
import dev.darshana.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<String> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @GetMapping("{name}")
    public List<ProductDto> getProductsForCategory(@PathVariable("name") String name) throws NotFoundException {
        return categoryService.getProductsForCategory(name);
    }

}
