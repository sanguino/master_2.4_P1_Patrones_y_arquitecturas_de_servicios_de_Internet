package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;

import java.util.Optional;

public interface CartUseCase {

    FullCartDto create();

    Optional<FullCartDto> findById(Long id);

    Optional<FullCartDto> finalizeById(Long id);
}
