package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.CartDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;

public interface CartUseCase {

    FullCartDto create(CartDto cartDto);

}
