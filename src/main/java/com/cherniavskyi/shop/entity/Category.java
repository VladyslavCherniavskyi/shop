package com.cherniavskyi.shop.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "category", schema = "shop")
public class Category {

    @Id
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
