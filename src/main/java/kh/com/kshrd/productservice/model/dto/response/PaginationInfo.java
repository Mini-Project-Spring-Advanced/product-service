package kh.com.kshrd.productservice.model.dto.response;

public record PaginationInfo(
        long totalElements,
        int currentPage,
        int pageSize,
        int totalPages
) {
}
