package kh.com.kshrd.productservice.model.enums;

import lombok.Getter;

@Getter
public enum ProductProperty {

    PRODUCT_ID("productId"),
    NAME("name"),
    PRICE("price"),
    QUANTITY("quantity"),
    CATEGORY_ID("categoryId"),
    USER_ID("userId");

    private final String property;

    ProductProperty(String property) {
        this.property = property;
    }

}
