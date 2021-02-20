package es.sanguino.ecommerce.infrastructure;

import es.sanguino.ecommerce.domain.FullProductDto;
import es.sanguino.ecommerce.domain.ProductRepository;
import es.sanguino.ecommerce.infrastructure.entity.ProductEntity;
import es.sanguino.ecommerce.infrastructure.repository.ProductJpaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements ProductRepository {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProductJpaRepository productJpaRepository;

    @Override
    public List<FullProductDto> findAll() {
        List<ProductEntity> productEntities = productJpaRepository.findAll();

        return productEntities
                .stream()
                .map(productEntity -> modelMapper.map(productEntity, FullProductDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public FullProductDto save(FullProductDto fullProductDto) {
        ProductEntity productEntity = modelMapper.map(fullProductDto, ProductEntity.class);
        ProductEntity savedProductEntity = productJpaRepository.save(productEntity);

        return modelMapper.map(savedProductEntity, FullProductDto.class);

    }

    @Override
    public Optional<FullProductDto> findById(Long id) {
        Optional<ProductEntity> productEntity = productJpaRepository.findById(id);

        return productEntity.map(entity -> modelMapper.map(entity, FullProductDto.class));
    }

    @Override
    public Optional<FullProductDto> deleteById(Long id) {
        Optional<FullProductDto> productEntity = this.findById(id);
        if (productEntity.isPresent()) {
            productJpaRepository.deleteById(id);
            return productEntity;
        }
        return Optional.empty();
    }
}
