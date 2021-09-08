package ru.bunkov.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import ru.bunkov.market.dto.ProductDto;
import ru.bunkov.market.model.Product;
import ru.bunkov.market.services.ProductService;
import ru.bunkov.market.exceptions.ResourceNotFoundException;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @GetMapping(value = "/{id}")
    public ProductDto findById(@PathVariable Long id) {
        Product p = productService.findById(id).orElseThrow(() -> new ResourceNotFoundException("Product not found, id: " + id));
        return new ProductDto(p);
    }

    @GetMapping
    public Page<ProductDto> findAll(@RequestParam(name = "p", defaultValue = "1") int pageIndex,
                                    @RequestParam (name = "minPrice", required = false)BigDecimal minPrice,
                                    @RequestParam(name = "maxPrice", required = false) BigDecimal maxPrice,
                                    @RequestParam (name = "title", required = false)String title
    ) {



        return productService.findPage(pageIndex - 1, 5, minPrice, maxPrice, title ).map(ProductDto::new);
    }

    @PostMapping
    public ProductDto createNewProduct(@RequestBody ProductDto newProductDto) {
        Product product = new Product();
        product.setPrice(newProductDto.getPrice());
        product.setTitle(newProductDto.getTitle());
        return new ProductDto(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
