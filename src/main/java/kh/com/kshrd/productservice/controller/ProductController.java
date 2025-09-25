package kh.com.kshrd.productservice.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import kh.com.kshrd.productservice.model.dto.request.ProductRequest;
import kh.com.kshrd.productservice.model.dto.response.APIResponse;
import kh.com.kshrd.productservice.model.dto.response.PagedResponse;
import kh.com.kshrd.productservice.model.dto.response.ProductResponse;
import kh.com.kshrd.productservice.model.enums.ProductProperty;
import kh.com.kshrd.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static kh.com.kshrd.productservice.utils.ResponseUtil.buildResponse;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@SecurityRequirement(name = "mini-project")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(
            summary = "Create a new product",
            description = "Creates a new product using the provided request payload. "
                          + "The request must include the product's name, price, and description.",
            tags = {"Product"}
    )
    public ResponseEntity<APIResponse<ProductResponse>> createProduct(
            @RequestBody @Valid ProductRequest request) {
        return buildResponse("Product created successfully",
                productService.createProduct(request), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(
            summary = "Get all products (paginated)",
            description = "Retrieves a paginated list of all products. "
                          + "Supports pagination, sorting by product properties, "
                          + "and direction (ASC/DESC).",
            tags = {"Product"}
    )
    public ResponseEntity<APIResponse<PagedResponse<List<ProductResponse>>>> getAllProducts(
            @RequestParam(defaultValue = "1") @Positive int page,
            @RequestParam(defaultValue = "10") @Positive int size,
            @RequestParam(defaultValue = "PRODUCT_ID", required = false) ProductProperty sortBy,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction direction) {
        return buildResponse("Products retrieved successfully",
                productService.getAllProducts(page, size, sortBy, direction), HttpStatus.OK);
    }

    @GetMapping("/{product-id}")
    @Operation(
            summary = "Get product by ID",
            description = "Retrieves a single product by its unique identifier (UUID). "
                          + "Returns the full product details if found.",
            tags = {"Product"}
    )
    public ResponseEntity<APIResponse<ProductResponse>> getProductById(
            @PathVariable("product-id") UUID productId) {
        return buildResponse("Product retrieved successfully",
                productService.getProductById(productId), HttpStatus.OK);
    }

    @PutMapping("/{product-id}")
    @Operation(
            summary = "Update product by ID",
            description = "Updates an existing product using the provided request payload. "
                          + "The product is identified by its UUID.",
            tags = {"Product"}
    )
    public ResponseEntity<APIResponse<ProductResponse>> updateProductById(
            @PathVariable("product-id") UUID productId,
            @RequestBody @Valid ProductRequest request) {
        return buildResponse("Product updated successfully",
                productService.updateProductById(productId, request), HttpStatus.OK);
    }

    @DeleteMapping("/{product-id}")
    @Operation(
            summary = "Delete product by ID",
            description = "Deletes an existing product identified by its UUID. "
                          + "After deletion, the product can no longer be retrieved.",
            tags = {"Product"}
    )
    public ResponseEntity<APIResponse<ProductResponse>> deleteProductById(
            @PathVariable("product-id") UUID productId) {
        productService.deleteProductById(productId);
        return buildResponse("Product deleted successfully", null, HttpStatus.OK);
    }
}
