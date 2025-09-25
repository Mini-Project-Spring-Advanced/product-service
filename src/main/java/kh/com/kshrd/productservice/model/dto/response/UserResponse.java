package kh.com.kshrd.productservice.model.dto.response;

import java.util.UUID;

public record UserResponse(
        UUID userId,
        String firstName,
        String lastName,
        String username,
        String email,
        String imageUrl) {
}
