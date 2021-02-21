package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.CartUseCase;
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

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper){
        CartService.modelMapper = modelMapper;
    }

    public CartService(CartUseCase cartUseCase) {
        this.cartUseCase = cartUseCase;
    }

    public static CartResponseDto mapper(FullCartDto fullCartDto) {
        CartResponseDto cartResponseDto = new CartResponseDto();
        cartResponseDto.setFinalized(fullCartDto.getFinalized());
        cartResponseDto.setId(fullCartDto.getId());

        HashMap<ProductResponseDto, Long> productsMap = new HashMap<>();
        fullCartDto.getProducts().forEach((key, value) -> {
            ProductResponseDto productResponseDto = modelMapper.map(key, ProductResponseDto.class);
            productsMap.put(productResponseDto, value);
        });
        cartResponseDto.setProducts(productsMap);
        return cartResponseDto;
    }

    public FullCartDto save() {
        return cartUseCase.create();
    }

    public CartResponseDto findById(Long id) {
        return cartUseCase
                .findById(id)
                .map(CartService::mapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    public CartResponseDto finalizeById(Long id) {
        return cartUseCase
                .finalizeById(id)
                .map(CartService::mapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    public CartResponseDto deleteById(Long id) {
        return cartUseCase
                .deleteById(id)
                .map(CartService::mapper)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cart not found"));
    }
}
