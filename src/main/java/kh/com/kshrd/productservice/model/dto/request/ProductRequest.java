package kh.com.kshrd.productservice.model.dto.request;


import java.math.BigDecimal;
import java.util.UUID;

public record ProductRequest(
        String name,
        BigDecimal price,
        Integer quantity,
        UUID categoryId
) {
}
