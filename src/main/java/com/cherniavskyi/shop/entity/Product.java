package com.cherniavskyi.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Entity
@Table(name = "product", schema = "shop")
public class Product {

    @Id
    @SequenceGenerator(name = "product_generator", sequenceName = "product_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_generator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "reference_image")
    private String referenceImage;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "stock_quantity")
    private Integer  stockQuantity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

}
