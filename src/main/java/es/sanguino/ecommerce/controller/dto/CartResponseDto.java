package es.sanguino.ecommerce.controller.dto;

import es.sanguino.ecommerce.domain.dto.FullProductDto;

import java.util.HashMap;

public class CartResponseDto {

    private Long id;
    private HashMap<FullProductDto, Long> products;
    private Boolean finalized = false;

    public CartResponseDto() {
    }

    public CartResponseDto(Long id, HashMap<FullProductDto, Long> products, Boolean finalized) {
        this.id = id;
        this.products = products;
        this.finalized = finalized;
    }

    public CartResponseDto(HashMap<FullProductDto, Long> products, Boolean finalized) {
        this(null, products, finalized);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<FullProductDto, Long> getProducts() {
        return products;
    }

    public void setProducts(HashMap<FullProductDto, Long> products) {
        this.products = products;
    }

    public Boolean getFinalized() {
        return finalized;
    }

    public void setFinalized(Boolean finalized) {
        this.finalized = finalized;
    }

    @Override
    public String toString() {
        return "FullCartDto{" +
                "id=" + id +
                ", products=" + products +
                ", finalized=" + finalized +
                '}';
    }
}
