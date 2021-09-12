package ru.bunkov.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.bunkov.market.model.Product;
import ru.bunkov.market.repositories.ProductRepository;
import ru.bunkov.market.repositories.specifications.ProductSpecifications;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public static final Function<Product, ru.bunkov.market.soap.Product> functionEntityToSoap = pr -> {
        ru.bunkov.market.soap.Product p = new ru.bunkov.market.soap.Product();
        p.setId(pr.getId());
        p.setTitle(pr.getTitle());
        p.setPrice(pr.getPrice().intValue());
        return p;
    };


    public List<ru.bunkov.market.soap.Product> getAllProducts() {
        return productRepository.findAll().stream().map(functionEntityToSoap).collect(Collectors.toList());
    }

    public ru.bunkov.market.soap.Product getById(Long id) {
        return productRepository.findById(id).map(functionEntityToSoap).get();
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Page<Product> findPage(int pageIndex, int pageSize, BigDecimal minPrice, BigDecimal maxPrice, String title) {

        Specification<Product> spec = Specification.where(null);

        if(title!=null&&!title.isBlank()){
            spec=spec.and(ProductSpecifications.titleLike(title));
        }

        if(minPrice!=null){
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if(maxPrice!=null){
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        return productRepository.findAll(spec ,PageRequest.of(pageIndex, pageSize));
    }

    public Product save(Product newProduct) {
        return productRepository.save(newProduct);
    }

    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
