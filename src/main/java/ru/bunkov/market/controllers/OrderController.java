package ru.bunkov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.bunkov.market.dto.OrderDto;
import ru.bunkov.market.exceptions.InvalidAttributeValueException;
import ru.bunkov.market.model.User;
import ru.bunkov.market.services.OrderService;
import ru.bunkov.market.services.UserService;
import ru.bunkov.market.exceptions.ResourceNotFoundException;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;

    @PostMapping
    public void createOrder(Principal principal, @RequestParam String address, @RequestParam String phone) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        if(address.isBlank()||phone.isBlank()){
            throw new InvalidAttributeValueException("Заполните все поля");
        }
        orderService.createOrder(user, address, phone);
    }

    @GetMapping
    public List<OrderDto> getAllOrders(Principal principal) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("Unable to create order. User not found"));
        return orderService.findAllDtosByUsername(principal.getName());
    }
}
