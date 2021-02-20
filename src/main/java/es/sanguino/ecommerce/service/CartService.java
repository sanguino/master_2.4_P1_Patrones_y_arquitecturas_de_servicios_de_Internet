package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.CartUseCase;
import es.sanguino.ecommerce.domain.ProductUseCase;
import es.sanguino.ecommerce.domain.dto.CartDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.dto.ProductDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
