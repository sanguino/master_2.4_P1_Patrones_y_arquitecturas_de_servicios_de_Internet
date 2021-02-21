package es.sanguino.ecommerce.infrastructure;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.CartRepository;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.infrastructure.entity.CartEntity;
import es.sanguino.ecommerce.infrastructure.entity.ProductEntity;
import es.sanguino.ecommerce.infrastructure.repository.CartJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Optional;

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

    @Override
    public Optional<FullCartDto> findById(Long id) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(id);

        return cartEntity.map(entity -> {
            FullCartDto fullCartDto = new FullCartDto();
            fullCartDto.setFinalized(entity.getFinalized());

            HashMap<FullProductDto, Long> fullProductDtoLongHashMap = new HashMap<>();
            entity.getProducts().forEach((key, value) -> {
                FullProductDto fullProductDto = modelMapper.map(key, FullProductDto.class);
                fullProductDtoLongHashMap.put(fullProductDto, value);
            });
            fullCartDto.setProducts(fullProductDtoLongHashMap);
            return fullCartDto;
        });
    }

}
