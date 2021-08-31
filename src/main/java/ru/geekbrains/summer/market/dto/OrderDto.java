package ru.geekbrains.summer.market.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.geekbrains.summer.market.model.Order;
import ru.geekbrains.summer.market.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@Data
public class OrderDto {
    private Long id;
    private String address;
    private String phone;
    private List<OrderItemDto> items;
    private BigDecimal price;

    public OrderDto(Order order) {
        this.id = order.getId();
        this.address = order.getAddress();
        this.phone = order.getPhone();
        this.items = order.getItems().stream().map(OrderItemDto::new).collect(Collectors.toList());
        this.price = order.getPrice();
    }
}
