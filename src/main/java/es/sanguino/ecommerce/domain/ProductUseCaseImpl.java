package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;

import java.util.Collection;
import java.util.Optional;

public class ProductUseCaseImpl implements ProductUseCase {

    private ProductRepository productRepository;

    public ProductUseCaseImpl(ProductRepository repository) {
        this.productRepository = repository;
    }

    @Override
    public Collection<FullProductDto> findAll() {
        return productRepository.findAll();
    }

    @Override
    public FullProductDto create(Product product) {
        FullProductDto fullProductDto = new FullProductDto(
                product.getName(),
                product.getPrice()
        );
        return productRepository.save(fullProductDto);
    }

    @Override
    public Optional<FullProductDto> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<FullProductDto> deleteById(Long id) {
        return productRepository.deleteById(id);
    }
}
