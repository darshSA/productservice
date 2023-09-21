package dev.darshana.productservice;

import dev.darshana.productservice.models.Price;
import dev.darshana.productservice.models.Product;
import dev.darshana.productservice.models.Category;
import dev.darshana.productservice.repositories.CategoryRepository;
import dev.darshana.productservice.repositories.PriceRepository;
import dev.darshana.productservice.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class ProductServiceApplication implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;
	private final PriceRepository priceRepository;

	public ProductServiceApplication(CategoryRepository categoryRepository,
									 ProductRepository productRepository,
									 PriceRepository priceRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
		this.priceRepository = priceRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Category category = new Category();
		category.setName("Apple Watches");
		//Category savedCategory = categoryRepository.save(category);

		Price price = new Price();
		price.setPrice(200);
		price.setCurrency("Rupee");
		//Price savedPrice = priceRepository.save(price);

		Product product = new Product();
		product.setTitle("Apple Watch 15 Pro");
		product.setDescription(" This is coolest watch");
		product.setCategory(category);
		product.setPrice(price);
		productRepository.save(product);

		productRepository.deleteById(UUID.fromString("aff5900c-2292-48f2-bf9b-b1f01fc3f017"));
		List<Product> products = productRepository.findAllByPrice_Currency("USD");
		System.out.println(products.size());

		List<Product> products1 = productRepository.findAllByTitle("Apple Watch 15 Pro");
		System.out.println(products1.size());
		/*Optional<Category> categoryOptional = categoryRepository.findById(UUID.fromString("a346bfb8-8db1-4173-92b9-343e36866f9b"));
		if(!categoryOptional.isEmpty()){
			Category category1 = categoryOptional.get();
		System.out.println("Category name is "+category1.getName());
		System.out.println("Printing products for category");

		for(Product product1:category1.getProducts()){
			System.out.println(product1.getTitle());
		}}*/
	}
}
