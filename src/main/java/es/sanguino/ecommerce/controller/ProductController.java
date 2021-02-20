package es.sanguino.ecommerce.controller;

import es.sanguino.ecommerce.domain.FullProductDto;
import es.sanguino.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Collection;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping("/api/products")
    public Collection<ProductResponseDto> getProducts() {
        return productService.findAll();
    }

    @PostMapping("/api/products")
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductResponseDto product) {

        FullProductDto fullProduct = productService.save(product);
        ProductResponseDto productResponseDto = modelMapper.map(fullProduct, ProductResponseDto.class);

        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(fullProduct.getId()).toUri();

        return ResponseEntity.created(location).body(productResponseDto);
    }

    @GetMapping("/api/products/{id}")
    public ProductResponseDto getProduct(@PathVariable long id) {
        return productService.findById(id).orElseThrow();
    }

    @DeleteMapping("/api/products/{id}")
    public ProductResponseDto deleteProduct(@PathVariable long id) {
        return productService.deleteById(id).orElseThrow();
    }


}