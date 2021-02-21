package es.sanguino.ecommerce.controller;

import es.sanguino.ecommerce.controller.dto.CartResponseDto;
import es.sanguino.ecommerce.controller.dto.ProductResponseDto;
import es.sanguino.ecommerce.domain.dto.FullCartDto;
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

        FullCartDto fullCartDto = cartService.save();
        CartResponseDto cartResponseDto = modelMapper.map(fullCartDto, CartResponseDto.class);

        URI location = fromCurrentRequest().path("/{id}")
                .buildAndExpand(fullCartDto.getId()).toUri();

        return ResponseEntity.created(location).body(cartResponseDto);
    }


    @GetMapping("/api/shoppingcarts/{id}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable Long id) {
        return ResponseEntity.ok(cartService.findById(id));
    }

}