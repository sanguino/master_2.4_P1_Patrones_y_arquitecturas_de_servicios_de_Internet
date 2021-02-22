package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.ProductUseCase;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
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

    public Optional<ProductResponseDto> findById(Long id) {
        return productUseCase
                .findById(id)
                .map(fullProductDto -> modelMapper.map(fullProductDto, ProductResponseDto.class));
    }

    public Optional<ProductResponseDto> deleteById(Long id) {
        return productUseCase
                .deleteById(id)
                .map(fullProductDto -> modelMapper.map(fullProductDto, ProductResponseDto.class));
    }

}
