package com.example.reviewing.product.service;

import com.example.reviewing.product.domain.ProductEntity;
import com.example.reviewing.product.repository.JpaProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final JpaProductRepository jpaProductRepository;

}
