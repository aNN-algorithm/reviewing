package com.example.reviewing.product.repository;

import com.example.reviewing.product.domain.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {

}
