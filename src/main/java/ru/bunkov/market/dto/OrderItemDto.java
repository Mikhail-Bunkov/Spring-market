package ru.bunkov.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.bunkov.market.model.OrderItem;
import ru.bunkov.market.model.Product;

import java.math.BigDecimal;

@NoArgsConstructor
@Data
public class OrderItemDto {
    private Long productId;
    private String productTitle;
    private BigDecimal pricePerProduct;
    private BigDecimal price;
    private int quantity;

    public OrderItemDto(Product product) {
        this.productId = product.getId();
        this.quantity = 1;
        this.pricePerProduct = product.getPrice();
        this.price = product.getPrice();
        this.productTitle = product.getTitle();
    }

    public OrderItemDto(OrderItem oi) {
        this.productId = oi.getId();
        this.quantity = oi.getQuantity();
        this.pricePerProduct = oi.getPricePerProduct();
        this.price = oi.getPrice();
        this.productTitle = oi.getProduct().getTitle();
    }

    public void changeQuantity(int amount) {
        quantity += amount;
        price = pricePerProduct.multiply(BigDecimal.valueOf(quantity));
    }
}
