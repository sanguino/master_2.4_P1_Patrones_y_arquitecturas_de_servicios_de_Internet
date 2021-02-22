package es.sanguino.ecommerce.controller;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
    public CartResponseDto patchCart(@PathVariable Long id) {
        return cartService.finalizeById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    @GetMapping("/api/shoppingcarts/{id}")
    public CartResponseDto getCart(@PathVariable Long id) {
        return cartService.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart not found"));
    }

    @DeleteMapping("/api/shoppingcarts/{id}")
    public CartResponseDto deleteCart(@PathVariable Long id) {
        return cartService.deleteById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "Cart not found"));
    }

    @PostMapping("/api/shoppingcarts/{cartId}/product/{prodId}/quantity/{prodQuantity}")
    public CartResponseDto addOrUpdateProduct(@PathVariable Long cartId, @PathVariable Long prodId, @PathVariable Long prodQuantity) {
        return cartService.addOrUpdateProduct(cartId, prodId, prodQuantity).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart or product not found"));
    }

    @DeleteMapping("/api/shoppingcarts/{cartId}/product/{prodId}")
    public CartResponseDto removeProduct(@PathVariable Long cartId, @PathVariable Long prodId) {
        return cartService.removeProduct(cartId, prodId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cart or product not found"));
    }
}