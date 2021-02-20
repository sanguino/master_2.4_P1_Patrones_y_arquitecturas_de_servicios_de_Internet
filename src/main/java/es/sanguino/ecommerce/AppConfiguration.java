package es.sanguino.ecommerce;

import es.sanguino.ecommerce.domain.ProductRepository;
import es.sanguino.ecommerce.domain.ProductUseCase;
import es.sanguino.ecommerce.domain.ProductUseCaseImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ProductUseCase productUseCase(ProductRepository productRepository) {
        return new ProductUseCaseImpl(productRepository);
    }

}
