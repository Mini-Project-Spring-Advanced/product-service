package kh.com.kshrd.productservice.service;

import kh.com.kshrd.productservice.model.dto.request.ProductRequest;
import kh.com.kshrd.productservice.model.dto.response.PagedResponse;
import kh.com.kshrd.productservice.model.dto.response.ProductResponse;
import kh.com.kshrd.productservice.model.enums.ProductProperty;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    ProductResponse createProduct(ProductRequest request);

    PagedResponse<List<ProductResponse>> getAllProducts(int page, int size, ProductProperty sortBy, Sort.Direction direction);

    ProductResponse getProductById(UUID productId);

    ProductResponse updateProductById(UUID productId, ProductRequest request);

    void deleteProductById(UUID productId);
}
