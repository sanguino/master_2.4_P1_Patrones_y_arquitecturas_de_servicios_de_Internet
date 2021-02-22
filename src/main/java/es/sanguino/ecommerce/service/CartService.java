package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductQuantityResponseDto;
import es.sanguino.ecommerce.domain.CartUseCase;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private CartUseCase cartUseCase;

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        CartService.modelMapper = modelMapper;
    }

    public CartService(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    public static CartResponseDto mapper(FullCartDto fullCartDto) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setFinalized(fullCartDto.getFinalized());
        cartResponseDto.setId(fullCartDto.getId());

        List<ProductQuantityResponseDto> products = new ArrayList<>();
        fullCartDto.getProducts().forEach((key, value) -> {
            ProductQuantityResponseDto productQuantityResponseDto = modelMapper.map(key, ProductQuantityResponseDto.class);
            productQuantityResponseDto.setQuantity(value);
            products.add(productQuantityResponseDto);
        });
        cartResponseDto.setProducts(products);
        return cartResponseDto;
    }

    public CartResponseDto save() {
        return CartService.mapper(cartUseCase.create());
    }

    public Optional<CartResponseDto> findById(Long id) {
        return cartUseCase
                .findById(id)
                .map(CartService::mapper);
    }

    public Optional<CartResponseDto> finalizeById(Long id) {
        return cartUseCase
                .finalizeById(id)
                .map(CartService::mapper);
    }

    public Optional<CartResponseDto> deleteById(Long id) {
        return cartUseCase
                .deleteById(id)
                .map(CartService::mapper);
    }

    public Optional<CartResponseDto> addOrUpdateProduct(Long cartId, Long prodId, Long prodQuantity) {
        return cartUseCase
                .addOrUpdateProduct(cartId, prodId, prodQuantity)
                .map(CartService::mapper);
    }

    public Optional<CartResponseDto> removeProduct(Long cartId, Long prodId) {
        return cartUseCase
                .removeProduct(cartId, prodId)
                .map(CartService::mapper);
    }
}
