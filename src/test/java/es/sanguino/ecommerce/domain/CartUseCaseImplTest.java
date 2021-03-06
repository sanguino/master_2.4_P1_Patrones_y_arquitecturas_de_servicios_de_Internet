package es.sanguino.ecommerce.domain;

import es.sanguino.ecommerce.domain.dto.FullCartDto;
import es.sanguino.ecommerce.domain.dto.FullProductDto;
import es.sanguino.ecommerce.domain.model.Cart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class CartUseCaseImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    CartUseCaseImpl cartUseCase;

    @BeforeEach
    public void before() {
        initMocks(this);
    }

    @Captor
    ArgumentCaptor<FullCartDto> argumentFullCartDto;

    @Test
    public void testWhenCreateCartThenRepositoryIsCalled() {
        FullCartDto fullCartDto = new FullCartDto();
        when(this.cartRepository.save(any(Cart.class))).thenReturn(fullCartDto);

        FullCartDto returnedFullCartDto = this.cartUseCase.create();
        verify(this.cartRepository, times(1)).save(any(Cart.class));
        assertEquals(returnedFullCartDto, fullCartDto);
    }

    @Test
    public void testGivenCartIdProdIdAndProdQuantityWhenAddProductAndCartExistsAndProductExistThenFullCartIsReturned() {
        Long cartId = 1L;
        Long prodId = 2L;
        Long prodQuantity = 3L;

        String prodName = "product1";
        Double prodPrice = 23.21;

        FullProductDto fullProductDto = new FullProductDto(prodName, prodPrice);
        FullCartDto fullCartDto = new FullCartDto(cartId, new HashMap<>(), false);
        when(this.cartRepository.findById(cartId)).thenReturn(Optional.of(fullCartDto));
        when(this.productRepository.findById(prodId)).thenReturn(Optional.of(fullProductDto));
        when(this.cartRepository.update(fullCartDto)).thenReturn(Optional.of(fullCartDto));

        Optional<FullCartDto> returnedFullCartDto = this.cartUseCase.addOrUpdateProduct(cartId, prodId, prodQuantity);
        verify(this.cartRepository, times(1)).findById(cartId);
        verify(this.productRepository, times(1)).findById(prodId);

        assertTrue(returnedFullCartDto.isPresent());
        assertTrue(returnedFullCartDto.get().getProducts().containsKey(fullProductDto));
    }

    @Test
    public void testGivenCartIdProdIdAndProdQuantityWhenAddProductAndCartExistsAndProductExistAndAlreadyAddedThenFullCartIsReturnedWithQuantityUpdated() {
        Long cartId = 1L;
        Long prodId = 2L;
        Long prodQuantity = 3L;
        Long currentQuantity = 4L;

        String prodName = "product1";
        Double prodPrice = 23.21;

        FullProductDto fullProductDto = new FullProductDto(prodName, prodPrice);
        HashMap<FullProductDto, Long> productsMap = new HashMap<>();
        productsMap.put(fullProductDto, currentQuantity);
        FullCartDto fullCartDto = new FullCartDto(cartId, productsMap, false);
        when(this.cartRepository.findById(cartId)).thenReturn(Optional.of(fullCartDto));
        when(this.productRepository.findById(prodId)).thenReturn(Optional.of(fullProductDto));
        when(this.cartRepository.update(fullCartDto)).thenReturn(Optional.of(fullCartDto));

        Optional<FullCartDto> returnedFullCartDto = this.cartUseCase.addOrUpdateProduct(cartId, prodId, prodQuantity);
        verify(this.cartRepository, times(1)).findById(cartId);
        verify(this.productRepository, times(1)).findById(prodId);

        assertTrue(returnedFullCartDto.isPresent());
        assertTrue(returnedFullCartDto.get().getProducts().containsKey(fullProductDto));
        assertFalse(returnedFullCartDto.get().getProducts().containsValue(currentQuantity));
        assertTrue(returnedFullCartDto.get().getProducts().containsValue(prodQuantity));
    }

    @Test
    public void testGivenCartIdProdIdAndProdQuantityWhenAddProductAndCartNotExistsThenOptionalEmptyIsReturned() {
        Long cartId = 1L;
        Long prodId = 2L;
        Long prodQuantity = 3L;

        String prodName = "product1";
        Double prodPrice = 23.21;

        FullProductDto fullProductDto = new FullProductDto(prodName, prodPrice);
        when(this.cartRepository.findById(cartId)).thenReturn(Optional.empty());
        when(this.productRepository.findById(prodId)).thenReturn(Optional.of(fullProductDto));

        Optional<FullCartDto> returnedFullCartDto = this.cartUseCase.addOrUpdateProduct(cartId, prodId, prodQuantity);
        verify(this.cartRepository, times(1)).findById(cartId);
        verify(this.productRepository, times(1)).findById(prodId);

        assertFalse(returnedFullCartDto.isPresent());
    }

    @Test
    public void testGivenCartIdProdIdAndProdQuantityWhenAddProductAndProdNotExistsThenOptionalEmptyIsReturned() {
        Long cartId = 1L;
        Long prodId = 2L;
        Long prodQuantity = 3L;

        FullCartDto fullCartDto = new FullCartDto(cartId, new HashMap<>(), false);
        when(this.cartRepository.findById(cartId)).thenReturn(Optional.of(fullCartDto));
        when(this.productRepository.findById(prodId)).thenReturn(Optional.empty());

        Optional<FullCartDto> returnedFullCartDto = this.cartUseCase.addOrUpdateProduct(cartId, prodId, prodQuantity);
        verify(this.cartRepository, times(1)).findById(cartId);
        verify(this.productRepository, times(1)).findById(prodId);

        assertFalse(returnedFullCartDto.isPresent());
    }

    @Test
    public void testGivenCartIdProdIdAndProdQuantityWhenAddProductAndProdAndCartNotExistsThenOptionalEmptyIsReturned() {
        Long cartId = 1L;
        Long prodId = 2L;
        Long prodQuantity = 3L;

        when(this.cartRepository.findById(cartId)).thenReturn(Optional.empty());
        when(this.productRepository.findById(prodId)).thenReturn(Optional.empty());

        Optional<FullCartDto> returnedFullCartDto = this.cartUseCase.addOrUpdateProduct(cartId, prodId, prodQuantity);
        verify(this.cartRepository, times(1)).findById(cartId);
        verify(this.productRepository, times(1)).findById(prodId);

        assertFalse(returnedFullCartDto.isPresent());
    }
}
