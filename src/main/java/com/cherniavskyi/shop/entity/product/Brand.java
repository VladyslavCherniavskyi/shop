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
@Table(name = "brand", schema = "shop")
public class Brand {

    @Id
    @SequenceGenerator(name = "brand_generator", sequenceName = "brand_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_generator")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "brands")
    private Set<Product> products;
}
