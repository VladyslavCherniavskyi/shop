package com.cherniavskyi.shop.repository.product;

import com.cherniavskyi.shop.entity.product.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

}