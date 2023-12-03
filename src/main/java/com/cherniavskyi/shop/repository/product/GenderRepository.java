package com.cherniavskyi.shop.repository.product;

import com.cherniavskyi.shop.entity.product.Gender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Integer> {

}