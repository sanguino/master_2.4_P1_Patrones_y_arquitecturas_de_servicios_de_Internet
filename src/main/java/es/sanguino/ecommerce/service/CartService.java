package es.sanguino.ecommerce.service;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    public FullCartDto save(CartResponseDto cart) {
        return new FullCartDto();
    }
}
