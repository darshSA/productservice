package dev.darshana.productservice.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.engine.internal.Cascade;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Product extends BaseModel{
    private String title;
    private String description;
    private String image;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Price price;
}
