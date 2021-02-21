package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.dto.FullProductDto;

import java.util.Optional;

public class CartUseCaseImpl implements CartUseCase{

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    public CartUseCaseImpl(CartRepository repository, ProductRepository productRepository) {
        this.cartRepository = repository;
        this.productRepository = productRepository;
    }

    @Override
    public FullCartDto create() {
        return this.cartRepository.save(new FullCartDto());
    }

    @Override
    public Optional<FullCartDto> findById(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public Optional<FullCartDto> finalizeById(Long id) {
        Optional<FullCartDto> fullCartDto = this.findById(id);
        if (fullCartDto.isPresent()) {
            fullCartDto.get().setFinalized(true);
            return Optional.of(cartRepository.update(fullCartDto.get()));
        }
        return fullCartDto;
    }

    @Override
    public Optional<FullCartDto> deleteById(Long id) {
        return cartRepository.deleteById(id);
    }

    @Override
    public Optional<FullCartDto> addProduct(Long cartId, Long prodId, Long prodQuantity) {
        Optional<FullCartDto> fullCartDto = this.findById(cartId);
        Optional<FullProductDto> fullProductDto = productRepository.findById(prodId);
        if (fullCartDto.isPresent() && fullProductDto.isPresent()) {
            fullCartDto.get().getProducts().put(fullProductDto.get(), prodQuantity);
            return Optional.of(cartRepository.update(fullCartDto.get()));
        }
        return fullCartDto;
    }

}
