package es.sanguino.ecommerce.infrastructure;

import es.sanguino.ecommerce.domain.CartRepository;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Cart;
import es.sanguino.ecommerce.domain.model.Product;
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

    private static HashMap<ProductEntity, Long> mapperHashMap2Entity(Map<Product, Long> productDtoMap) {
        HashMap<ProductEntity, Long> productsMap = new HashMap<>();
        productDtoMap.forEach((key, value) -> {
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
    public FullCartDto save(Cart cart) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setFinalized(cart.getFinalized());

        cartEntity.setProducts(CartRepositoryAdapter.mapperHashMap2Entity(cart.getProducts()));

        CartEntity savedCartEntity = cartJpaRepository.save(cartEntity);

        return modelMapper.map(savedCartEntity, FullCartDto.class);
    }

    @Override
    public Optional<FullCartDto> findById(Long id) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(id);

        return cartEntity.map(CartRepositoryAdapter::mapperCartEntity2FullCartDto);
    }

    @Override
    public Optional<FullCartDto> update(FullCartDto fullCartDto) {
        Optional<CartEntity> cartEntity = cartJpaRepository.findById(fullCartDto.getId());
        if (cartEntity.isPresent()) {
            cartEntity.get().setFinalized(fullCartDto.getFinalized());
            cartEntity.get().setProducts(CartRepositoryAdapter.mapperHashMapFull2Entity(fullCartDto.getProducts()));
            cartJpaRepository.save(cartEntity.get());

            return Optional.of(CartRepositoryAdapter.mapperCartEntity2FullCartDto(cartEntity.get()));
        }
        return Optional.empty();
    }

    @Override
    public Optional<FullCartDto> deleteById(Long id) {
        Optional<FullCartDto> cartEntity = this.findById(id);

        if (cartEntity.isPresent()) {
            cartJpaRepository.deleteById(id);
            return cartEntity;
        }
        return Optional.empty();
    }

}
