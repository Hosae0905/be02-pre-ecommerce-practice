package com.example.ecommerce.pay.repository;

import com.example.ecommerce.pay.model.entity.Pay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Pay, Long> {
}
