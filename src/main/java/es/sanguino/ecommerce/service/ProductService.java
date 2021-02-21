package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.ProductUseCase;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private ProductUseCase productUseCase;

    @Autowired
    private ModelMapper modelMapper;

    public ProductService(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    public Collection<ProductResponseDto> findAll() {
        return productUseCase
                .findAll()
                .stream()
                .map(fullProductDto -> modelMapper.map(fullProductDto, ProductResponseDto.class))
                .collect(Collectors.toList());
    }

    public FullProductDto save(ProductResponseDto productResponseDto) {
        Product product = modelMapper.map(productResponseDto, Product.class);
        return productUseCase.create(product);
    }

    public ProductResponseDto findById(Long id) {
        return productUseCase
                .findById(id)
                .map(fullProductDto -> modelMapper.map(fullProductDto, ProductResponseDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    public ProductResponseDto deleteById(Long id) {
        return productUseCase
                .deleteById(id)
                .map(fullProductDto -> modelMapper.map(fullProductDto, ProductResponseDto.class))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Product not found"));
    }

}
