package com.example.ecommerce.product.repository;

import com.example.ecommerce.product.model.entity.Product;
import com.example.ecommerce.product.repository.querydsl.ProductRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {

    @Query("SELECT p FROM Product p JOIN FETCH p.imageList ")
    public List<Product> findAllQuery();

    @Query(nativeQuery = true, value = "SELECT * FROM product AS p"
            + " LEFT JOIN image AS pi"
            + " ON p.id = pi.product_id"
            + " LIMIT :page, :size")
    public List<Product> findAllQueryWithPage(Integer page, Integer size);


//    @Lock(LockModeType.PESSIMISTIC_WRITE)       // 안전하지만 속도가 느려진다.
//    public Optional<Product> findById(Long id);

//    @Lock(LockModeType.OPTIMISTIC)       // 안전하지만 속도가 느려진다.
//    public Optional<Product> findById(Long id);

}
