package es.sanguino.ecommerce.controller;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

@RestController
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping("/api/shoppingcarts")
    public ResponseEntity<CartResponseDto> createCart() {
        CartResponseDto cartResponseDto = cartService.save();
        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(cartResponseDto.getId()).toUri();

        return ResponseEntity.created(location).body(cartResponseDto);
    }

    @PatchMapping("/api/shoppingcarts/{id}")
    public ResponseEntity<CartResponseDto> patchCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.finalizeById(id));
    }

    @GetMapping("/api/shoppingcarts/{id}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

    @DeleteMapping("/api/shoppingcarts/{id}")
    public ResponseEntity<CartResponseDto> deleteCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.deleteById(id));
    }

    @PostMapping("/api/shoppingcarts/{cartId}/product/{prodId}/quantity/{prodQuantity}")
    public ResponseEntity<CartResponseDto> createCart(@PathVariable Long cartId, @PathVariable Long prodId, @PathVariable Long prodQuantity) {
        return ResponseEntity.ok(cartService.addProduct(cartId, prodId, prodQuantity));
    }
}