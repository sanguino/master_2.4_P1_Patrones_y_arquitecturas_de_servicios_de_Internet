package es.sanguino.ecommerce;

import es.sanguino.ecommerce.domain.*;
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

    @Bean
    public CartUseCase productUseCase(CartRepository cartRepository) {
        return new CartUseCaseImpl(cartRepository);
    }

}
