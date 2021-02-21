package es.sanguino.ecommerce.domain;


import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductUseCaseImplTest {
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductUseCaseImpl productUseCase;

    @Captor
    ArgumentCaptor<FullProductDto> argumentFullProductDto;

    @Captor
    ArgumentCaptor<Long> argumentLong;

    @Before
    public void before() {
        initMocks(this);
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

    @Test
    public void testGivenIdWhenDeleteProductThenRepositoryIsCalled() {
        Long id = 1L;
        String prodName = "product1";
        Double prodPrice = 23.21;
        FullProductDto fullProductDto = new FullProductDto(id, prodName, prodPrice);
        when(this.productRepository.deleteById(any(Long.class))).thenReturn(Optional.of(fullProductDto));


        this.productUseCase.deleteById(id);
        verify(this.productRepository, times(1)).deleteById(this.argumentLong.capture());
        assertEquals(id, this.argumentLong.getValue());
    }
}
