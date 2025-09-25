package kh.com.kshrd.productservice.client;

import kh.com.kshrd.productservice.model.dto.response.APIResponse;
import kh.com.kshrd.productservice.model.dto.response.CategoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "category-service", path = "/api/v1/categories")
public interface CategoryClient {

    @GetMapping("/{category-id}")
    ResponseEntity<APIResponse<CategoryResponse>> getCategoryById(@PathVariable("category-id")UUID categoryId);

}
