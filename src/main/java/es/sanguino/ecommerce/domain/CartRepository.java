package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;

import java.util.Optional;

public interface CartRepository {

    FullCartDto save(FullCartDto fullCartDto);

    Optional<FullCartDto> findById(Long id);

    FullCartDto update(FullCartDto fullCartDto);

    Optional<FullCartDto> deleteById(Long id);
}
