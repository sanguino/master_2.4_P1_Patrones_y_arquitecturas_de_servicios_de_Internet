package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;

public interface CartRepository {

    FullCartDto save(FullCartDto fullCartDto);

}
