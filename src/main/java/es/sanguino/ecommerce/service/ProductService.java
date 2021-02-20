package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.ProductResponseDto;
import es.sanguino.ecommerce.domain.FullProductDto;
import es.sanguino.ecommerce.domain.ProductDto;
import es.sanguino.ecommerce.domain.ProductUseCase;
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
        ProductDto productDto = modelMapper.map(productResponseDto, ProductDto.class);
        return productUseCase.create(productDto);
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
