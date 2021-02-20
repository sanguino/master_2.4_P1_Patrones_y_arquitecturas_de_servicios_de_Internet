package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.CartDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;

public class CartUseCaseImpl implements CartUseCase{

    private CartRepository cartRepository;

    public CartUseCaseImpl(CartRepository repository) {
        this.cartRepository = repository;
    }

    @Override
    public FullCartDto create(CartDto cartDto) {
        return null;
    }
}
