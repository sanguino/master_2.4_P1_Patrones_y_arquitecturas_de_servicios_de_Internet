package es.sanguino.ecommerce.domain;

import java.util.Collection;
import java.util.Optional;

public interface ProductUseCase {

    Collection<FullProductDto> findAll();

    FullProductDto create(ProductDto book);

    Optional<FullProductDto> findById(Long id);

}
