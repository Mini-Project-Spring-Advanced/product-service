package kh.com.kshrd.productservice.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public record APIResponse<T>(
        String message,
        @JsonInclude(JsonInclude.Include.NON_NULL) T payload,
        HttpStatus status,
        Instant instant
) {
}
