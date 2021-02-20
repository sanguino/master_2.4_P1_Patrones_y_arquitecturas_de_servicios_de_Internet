package es.sanguino.ecommerce.infrastructure;

import es.sanguino.ecommerce.domain.CartRepository;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.infrastructure.entity.CartEntity;
import es.sanguino.ecommerce.infrastructure.entity.ProductEntity;
import es.sanguino.ecommerce.infrastructure.repository.CartJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class CartRepositoryAdapter implements CartRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CartJpaRepository cartJpaRepository;

    @Override
    public FullCartDto save(FullCartDto fullCartDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setFinalized(fullCartDto.getFinalized());

        HashMap<ProductEntity, Long> productsMap = new HashMap<>();
        fullCartDto.getProducts().forEach((key, value) -> {
            ProductEntity productEntity = modelMapper.map(key, ProductEntity.class);
            productsMap.put(productEntity, value);
        });
        cartEntity.setProducts(productsMap);

        CartEntity savedCartEntity = cartJpaRepository.save(cartEntity);

        return modelMapper.map(savedCartEntity, FullCartDto.class);
    }

}
