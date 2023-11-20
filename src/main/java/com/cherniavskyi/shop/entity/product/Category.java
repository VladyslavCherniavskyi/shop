package com.cherniavskyi.shop.entity.product;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "category", schema = "shop")
public class Category {

    @Id
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    private Integer id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;
}
