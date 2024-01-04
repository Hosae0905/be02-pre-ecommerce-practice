package com.example.ecommerce.product.repository;

import com.example.ecommerce.product.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
