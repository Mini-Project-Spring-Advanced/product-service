package kh.com.kshrd.productservice.model.dto.response;

import java.util.UUID;

public record CategoryResponse(
        UUID categoryId,
        String name,
        String description
) {
}
