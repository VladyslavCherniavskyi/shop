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
@Table(name = "size", schema = "shop")
public class Size {

    @Id
    @SequenceGenerator(name = "size_generator", sequenceName = "size_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "size_generator")
    private Integer id;

    @Column(name = "international", length = 3)
    private String international;

    @Column(name = "european")
    private Integer european;

    @ManyToMany(mappedBy = "sizes")
    private Set<Product> products;
}
