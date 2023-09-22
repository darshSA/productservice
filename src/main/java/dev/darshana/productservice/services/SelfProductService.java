package dev.darshana.productservice.services;

import dev.darshana.productservice.dtos.GenericProductDto;
import dev.darshana.productservice.dtos.ProductDto;
import dev.darshana.productservice.exceptions.NotFoundException;
import dev.darshana.productservice.models.Category;
import dev.darshana.productservice.models.Price;
import dev.darshana.productservice.models.Product;
import dev.darshana.productservice.repositories.CategoryRepository;
import dev.darshana.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("selfProductServiceImpl")
public class SelfProductService {//implements ProductService{
    private ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository,
                              CategoryRepository categoryRepository){
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }
    public ProductDto convertToProductDto(Product product){
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getUuid().toString());
        productDto.setTitle(product.getTitle());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice().getPrice());
        productDto.setCurrency(product.getPrice().getCurrency());
        productDto.setCategory(product.getCategory().getName());
        productDto.setImage(product.getImage());
        return productDto;
    }
    public ProductDto getProductById(String id) throws NotFoundException, IllegalArgumentException {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if(product.isEmpty())
            throw new NotFoundException("Product with id "+id+" does not exist");
        return convertToProductDto(product.get());
    }

    public ProductDto createProduct(ProductDto productDto) {
        return convertToProductDto(productRepository.save(convertDtoToProduct(productDto)));
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for(Product product:products){
            productDtos.add(convertToProductDto(product));
        }
        return productDtos;
    }

    public String deleteProduct(String id) throws NotFoundException, IllegalArgumentException {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if(product.isEmpty())
            throw new NotFoundException("Product with id "+id+" does not exist");
        else{
            productRepository.delete(product.get());
            return "Product with id "+id+" is deleted";
        }
    }

    /*public ProductDto updateProductById(ProductDto productDto, String id) throws NotFoundException, IllegalArgumentException {
        Optional<Product> product = productRepository.findById(UUID.fromString(id));
        if(product.isEmpty())
            throw new NotFoundException("Product with id "+id+" does not exist");

        Optional<Product> product1 = productRepository.updateProductByUuid(UUID.fromString(id),
                convertDtoToProduct(productDto));
        return convertToProductDto(product1.get());
    }*/

    private Product convertDtoToProduct(ProductDto productDto) {
        Product product1 = new Product();
        product1.setTitle(productDto.getTitle());
        product1.setDescription(productDto.getDescription());

        Optional<Category> category = categoryRepository.findByNameIgnoreCase(productDto.getCategory());
        if(category.isEmpty()) {
            Category category1 = new Category();
            category1.setName(productDto.getCategory());
            product1.setCategory(category1);
        }
        else
            product1.setCategory(category.get());

        Price price = new Price(productDto.getCurrency(), productDto.getPrice());
        product1.setPrice(price);
        product1.setImage(productDto.getImage());
        return product1;
    }
}
