package com.example.ecommerce.product.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    private Product product;

    public static Image buildEntity(String saveFile, Long idx) {
        return Image.builder()
                .image(saveFile)
                .product(Product.builder().id(idx).build())
                .build();
    }
}
