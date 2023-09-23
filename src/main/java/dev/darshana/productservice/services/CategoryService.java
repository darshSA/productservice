package dev.darshana.productservice.services;

import dev.darshana.productservice.dtos.CategoryDto;
import dev.darshana.productservice.dtos.ProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;
import dev.darshana.productservice.models.Category;
import dev.darshana.productservice.models.Product;
import dev.darshana.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;
    private SelfProductService selfProductService;

    public CategoryService(CategoryRepository categoryRepository,
                           SelfProductService selfProductService){
        this.categoryRepository = categoryRepository;
        this.selfProductService = selfProductService;
    }

    public List<String> getAllCategories(){
        List<Category> categories = categoryRepository.findAll();
        List<String> categoryList = new ArrayList<>();
        for(Category category: categories){
            categoryList.add(category.getName());
        }
        return categoryList;
    }

    private CategoryDto convertToCategoryDto(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getUuid().toString());
        categoryDto.setName(category.getName());
        //categoryDto.setProducts(category.getProducts());
        return categoryDto;
    }

    public List<ProductDto> getProductsForCategory(String name) throws NotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findByNameIgnoreCase(name);
        if(categoryOptional.isEmpty())
            throw new NotFoundException("Category with name "+name+" does not exist");

        List<Product> products = categoryOptional.get().getProducts();
        if(products.isEmpty())
            throw new NotFoundException("There are no products with category "+name+" currently");
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:products){
            productDtos.add(selfProductService.convertToProductDto(product));
        }
        return productDtos;
    }
}
