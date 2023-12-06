package com.cherniavskyi.shop.repository.product.photo;

import com.cherniavskyi.shop.entity.product.photo.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, UUID> {
}
