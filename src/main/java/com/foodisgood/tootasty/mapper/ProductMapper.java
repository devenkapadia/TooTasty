package com.foodisgood.tootasty.mapper;

import com.foodisgood.tootasty.dto.ProductRequest;
import com.foodisgood.tootasty.entity.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public Product toEntity(ProductRequest.ProductCreateRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }

    public Product toEntity(ProductRequest.ProductUpdateRequest request) {
        return Product.builder()
                .name(request.name())
                .price(request.price())
                .build();
    }
}
