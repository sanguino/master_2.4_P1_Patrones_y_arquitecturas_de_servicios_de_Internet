package es.sanguino.ecommerce.controller;

import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

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
    public ProductResponseDto getProduct(@PathVariable Long id) {
        return productService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @DeleteMapping("/api/products/{id}")
    public ProductResponseDto deleteProduct(@PathVariable Long id) {
        return productService.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Product not found"));
    }

}