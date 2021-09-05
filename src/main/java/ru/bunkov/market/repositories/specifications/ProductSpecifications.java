package ru.bunkov.market.repositories.specifications;

import org.springframework.data.jpa.domain.Specification;
import ru.bunkov.market.model.Product;

import java.math.BigDecimal;

public class ProductSpecifications {
    public static Specification<Product> priceGreaterOrEqualsThan(BigDecimal minPrice){
        return (root,criteriaQuery, criteriaBuilder) -> criteriaBuilder.greaterThanOrEqualTo(root.get("price"),minPrice);
    }
    public static  Specification<Product> priceLesserOrEqualsThan(BigDecimal maxPrice){
        return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), maxPrice);
    }
    public static Specification<Product> titleLike(String title){
        return (root,criteriaQuery, criteriaBuilder) -> criteriaBuilder.like(root.get("title"), "%"+title+"%");
    }
}
