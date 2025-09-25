package kh.com.kshrd.productservice.service.servceimpl;

import kh.com.kshrd.productservice.client.CategoryClient;
import kh.com.kshrd.productservice.client.UserClient;
import kh.com.kshrd.productservice.exception.ConflictException;
import kh.com.kshrd.productservice.exception.NotFoundException;
import kh.com.kshrd.productservice.model.dto.request.ProductRequest;
import kh.com.kshrd.productservice.model.dto.response.CategoryResponse;
import kh.com.kshrd.productservice.model.dto.response.PagedResponse;
import kh.com.kshrd.productservice.model.dto.response.ProductResponse;
import kh.com.kshrd.productservice.model.dto.response.UserResponse;
import kh.com.kshrd.productservice.model.entity.Product;
import kh.com.kshrd.productservice.model.enums.ProductProperty;
import kh.com.kshrd.productservice.repository.ProductRepository;
import kh.com.kshrd.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import static kh.com.kshrd.productservice.utils.ResponseUtil.pageResponse;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserClient userClient;
    private final CategoryClient categoryClient;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        boolean existsProduct = productRepository.existsByNameIgnoreCase(request.name());
        if (existsProduct) {
            throw new ConflictException("Product name already exists");
        }

        UserResponse userResponse = Objects.requireNonNull(
                userClient.getUserInfo().getBody()
        ).payload();

        CategoryResponse categoryResponse = Objects.requireNonNull(
                categoryClient.getCategoryById(request.categoryId()).getBody()
        ).payload();


        Product product = Product.builder()
                .name(request.name())
                .price(request.price())
                .quantity(request.quantity())
                .categoryId(categoryResponse.categoryId())
                .userId(userResponse.userId())
                .build();

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getProductId(),
                saved.getName(),
                saved.getPrice(),
                saved.getQuantity(),
                categoryResponse,
                userResponse
        );
    }

    @Override
    public PagedResponse<List<ProductResponse>> getAllProducts(int page, int size,
                                                               ProductProperty sortBy,
                                                               Sort.Direction direction) {

        UserResponse userResponse = Objects.requireNonNull(
                userClient.getUserInfo().getBody()
        ).payload();

        int zeroBased = Math.max(page, 1) - 1;

        Pageable pageable = PageRequest.of(
                zeroBased,
                size,
                Sort.by(direction, sortBy.getProperty())
        );

        Page<Product> pageProducts = productRepository.findAll(pageable);

        List<ProductResponse> items = pageProducts.getContent()
                .stream()
                .map(product -> {
                    CategoryResponse categoryResponse = Objects.requireNonNull(
                            categoryClient.getCategoryById(product.getCategoryId()).getBody()
                    ).payload();

                    return new ProductResponse(
                            product.getProductId(),
                            product.getName(),
                            product.getPrice(),
                            product.getQuantity(),
                            categoryResponse,
                            userResponse
                    );
                })
                .toList();

        return pageResponse(
                items,
                pageProducts.getTotalElements(),
                page,
                size,
                pageProducts.getTotalPages()
        );
    }

    @Override
    public ProductResponse getProductById(UUID productId) {
        UserResponse userResponse = Objects.requireNonNull(
                userClient.getUserInfo().getBody()
        ).payload();

        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product " + productId + " not found")
        );

        CategoryResponse categoryResponse = Objects.requireNonNull(
                categoryClient.getCategoryById(product.getCategoryId()).getBody()
        ).payload();

        return new ProductResponse(
                product.getProductId(),
                product.getName(),
                product.getPrice(),
                product.getQuantity(),
                categoryResponse,
                userResponse
        );
    }

    @Override
    public ProductResponse updateProductById(UUID productId, ProductRequest request) {

        boolean existsProduct = productRepository.existsByNameIgnoreCase(request.name());

        if (existsProduct) {
            throw new ConflictException("Product name already exists");
        }

        UserResponse userResponse = Objects.requireNonNull(
                userClient.getUserInfo().getBody()
        ).payload();

        CategoryResponse categoryResponse = Objects.requireNonNull(
                categoryClient.getCategoryById(request.categoryId()).getBody()
        ).payload();


        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product " + productId + " not found")
        );

        product.setName(request.name());
        product.setPrice(request.price());
        product.setQuantity(request.quantity());
        product.setCategoryId(request.categoryId());

        Product saved = productRepository.save(product);

        return new ProductResponse(
                saved.getProductId(),
                saved.getName(),
                saved.getPrice(),
                saved.getQuantity(),
                categoryResponse,
                userResponse
        );
    }

    @Override
    public void deleteProductById(UUID productId) {
        Product product = productRepository.findById(productId).orElseThrow(
                () -> new NotFoundException("Product " + productId + " not found")
        );
        productRepository.delete(product);
    }
}
