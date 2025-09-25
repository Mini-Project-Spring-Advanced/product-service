package kh.com.kshrd.productservice.model.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ProductResponse(
        UUID productId,
        String name,
        BigDecimal price,
        Integer quantity,
        CategoryResponse categoryResponse,
        UserResponse userResponse
) {
}
