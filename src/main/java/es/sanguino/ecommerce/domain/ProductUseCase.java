package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;

import java.util.Collection;
import java.util.Optional;

public interface ProductUseCase {

    Collection<FullProductDto> findAll();

    FullProductDto create(Product product);

    Optional<FullProductDto> findById(Long id);

    Optional<FullProductDto> deleteById(Long id);

}
