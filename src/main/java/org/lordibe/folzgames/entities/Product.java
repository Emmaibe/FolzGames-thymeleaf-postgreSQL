package org.lordibe.folzgames.entities;

import jakarta.persistence.*;
import lombok.*;
import org.lordibe.folzgames.enums.Categories;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String category;
    private Integer quantity;
    private Double price;
    private String images;
}
