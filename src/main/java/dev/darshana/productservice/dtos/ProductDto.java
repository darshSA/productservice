package dev.darshana.productservice.dtos;

import dev.darshana.productservice.models.Category;
import dev.darshana.productservice.models.Price;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private String id;
    private String title;
    private double price;
    private String currency;
    private String category;
    private String description;
    private String image;
}
