package es.sanguino.ecommerce.domain;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    List<FullProductDto> findAll();

    FullProductDto save(FullProductDto fullProductDto);

    Optional<FullProductDto> findById(Long id);

    Optional<FullProductDto> deleteById(Long id);

}
