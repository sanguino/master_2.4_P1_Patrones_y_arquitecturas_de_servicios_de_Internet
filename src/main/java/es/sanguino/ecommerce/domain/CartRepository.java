package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.model.Cart;

import java.util.Optional;

public interface CartRepository {

    FullCartDto save(Cart cart);

    Optional<FullCartDto> findById(Long id);

    FullCartDto update(FullCartDto fullCartDto);

    Optional<FullCartDto> deleteById(Long id);
}
