package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.CartDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;

import java.util.Optional;

public interface CartUseCase {

    FullCartDto create(CartDto cartDto);

    Optional<FullCartDto> findById(Long id);

}
