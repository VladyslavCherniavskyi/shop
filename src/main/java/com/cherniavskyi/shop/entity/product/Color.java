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
@Table(name = "color", schema = "shop")
public class Color {

    @Id
    @SequenceGenerator(name = "color_generator", sequenceName = "color_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "color_generator")
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "html_code", length = 10)
    private String htmlCode;

    @Column(name = "red_component")
    private Integer redComponent;

    @ManyToMany(mappedBy = "colors")
    private Set<Product> products;
}
