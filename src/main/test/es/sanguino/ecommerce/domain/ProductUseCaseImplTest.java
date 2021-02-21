package es.sanguino.ecommerce.domain;


import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductUseCaseImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductUseCaseImpl productUseCase;

    @Captor
    ArgumentCaptor<FullProductDto> argumentFullProductDto;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
    }

    public FullProductDto create(Product product) {
        FullProductDto fullProductDto = new FullProductDto(
                product.getName(),
                product.getPrice()
        );
        return productRepository.save(fullProductDto);
    }

    @Test
    public void testGivenNewProductWhenSaveProductThenDataIsOk() {
        String prodName = "product1";
        Double prodPrice = 23.21;
        Product product = new Product(prodName, prodPrice);
        FullProductDto fullProductDto = new FullProductDto(1L, prodName, prodPrice);
        when(this.productRepository.save(any(FullProductDto.class))).thenReturn(fullProductDto);

        FullProductDto returnedFullProductDto = this.productUseCase.create(product);
        verify(this.productRepository, times(1)).save(this.argumentFullProductDto.capture());
        FullProductDto capturedFullProductDto = this.argumentFullProductDto.getValue();
        assertEquals(returnedFullProductDto.getName(), capturedFullProductDto.getName());
        assertEquals(returnedFullProductDto.getPrice(), capturedFullProductDto.getPrice());
    }
}
