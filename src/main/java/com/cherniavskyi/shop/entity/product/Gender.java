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
@Table(name = "gender", schema = "shop")
public class Gender {

    @Id
    @SequenceGenerator(name = "gender_generator", sequenceName = "gender_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gender_generator")
    private Integer id;

    @Column(name = "gender", nullable = false, unique = true, length = 50)
    private String gender;

    @ManyToMany(mappedBy = "genders")
    private Set<Product> products;
}
