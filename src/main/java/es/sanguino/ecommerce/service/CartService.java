package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.CartUseCase;
import es.sanguino.ecommerce.domain.dto.CartDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;

@Service
public class CartService {

    private CartUseCase cartUseCase;

    @Autowired
    private ModelMapper modelMapper;

    public CartService(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    public FullCartDto save(CartResponseDto cartResponseDto) {
        CartDto cartDto = modelMapper.map(cartResponseDto, CartDto.class);
        return cartUseCase.create(cartDto);
    }

    public CartResponseDto findById(Long id) {
        return cartUseCase
                .findById(id)
                .map(fullCartDto -> {
                    CartResponseDto cartResponseDto = new CartResponseDto();
                    cartResponseDto.setFinalized(fullCartDto.getFinalized());

                    HashMap<ProductResponseDto, Long> productsMap = new HashMap<>();
                    fullCartDto.getProducts().forEach((key, value) -> {
                        ProductResponseDto productResponseDto = modelMapper.map(key, ProductResponseDto.class);
                        productsMap.put(productResponseDto, value);
                    });
                    cartResponseDto.setProducts(productsMap);
                    return cartResponseDto;
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }
}
