package kh.com.kshrd.productservice.model.dto.response;

public record PagedResponse<T>(
        T items,
        PaginationInfo pagination
) {
}
