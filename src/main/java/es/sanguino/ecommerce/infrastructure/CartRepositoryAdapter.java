package es.sanguino.ecommerce.infrastructure;

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
import java.util.Map;
import java.util.Optional;

@Component
public class CartRepositoryAdapter implements CartRepository {

    private static ModelMapper modelMapper;

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        CartRepositoryAdapter.modelMapper = modelMapper;
    }

    private static HashMap<ProductEntity, Long> mapperHashMapFull2Entity(Map<FullProductDto, Long> fullMap) {
        HashMap<ProductEntity, Long> productsMap = new HashMap<>();
        fullMap.forEach((key, value) -> {
            ProductEntity productEntity = modelMapper.map(key, ProductEntity.class);
            productsMap.put(productEntity, value);
        });
        return productsMap;
    }

    private static FullCartDto mapperCartEntity2FullCartDto(CartEntity cartEntity) {
        FullCartDto fullCartDto = new FullCartDto();
        fullCartDto.setFinalized(cartEntity.getFinalized());
        fullCartDto.setId(cartEntity.getId());

        HashMap<FullProductDto, Long> fullProductDtoLongHashMap = new HashMap<>();
        cartEntity.getProducts().forEach((key, value) -> {
            FullProductDto fullProductDto = modelMapper.map(key, FullProductDto.class);
            fullProductDtoLongHashMap.put(fullProductDto, value);
        });
        fullCartDto.setProducts(fullProductDtoLongHashMap);
        return fullCartDto;
    }

    @Autowired
    private CartJpaRepository cartJpaRepository;

    @Override
    public FullCartDto save(FullCartDto fullCartDto) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setFinalized(fullCartDto.getFinalized());

        cartEntity.setProducts(CartRepositoryAdapter.mapperHashMapFull2Entity(fullCartDto.getProducts()));

        CartEntity savedCartEntity = cartJpaRepository.save(cartEntity);

        return modelMapper.map(savedCartEntity, FullCartDto.class);
    }

    @Override
    public Optional<FullCartDto> findById(Long id) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(id);

        return cartEntity.map(CartRepositoryAdapter::mapperCartEntity2FullCartDto);
    }

    @Override
    public FullCartDto update(FullCartDto fullCartDto) {
        CartEntity cartEntity = cartJpaRepository.findById(fullCartDto.getId()).get();
        cartEntity.setFinalized(fullCartDto.getFinalized());
        cartEntity.setProducts(CartRepositoryAdapter.mapperHashMapFull2Entity(fullCartDto.getProducts()));
        cartJpaRepository.save(cartEntity);

        return CartRepositoryAdapter.mapperCartEntity2FullCartDto(cartEntity);
    }

}
