package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;

import java.util.Optional;

public class CartUseCaseImpl implements CartUseCase{

    private CartRepository cartRepository;

    public CartUseCaseImpl(CartRepository repository) {
        this.cartRepository = repository;
    }

    @Override
    public FullCartDto create() {
        return this.cartRepository.save(new FullCartDto());
    }

    @Override
    public Optional<FullCartDto> findById(Long id) {
        return cartRepository.findById(id);
    }

}
