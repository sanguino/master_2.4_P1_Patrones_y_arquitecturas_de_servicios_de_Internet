package es.sanguino.ecommerce.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class CartDto {


    private Map<ProductDto, Long> products;
    private Boolean finalized;

    public CartDto() {
        this.finalized = false;
        this.products = new HashMap<>();
    }

    public CartDto(Map<ProductDto, Long> products, Boolean finalized) {
        this.products = products;
        this.finalized = finalized;
    }

    public Map<ProductDto, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductDto, Long> products) {
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
                ", products=" + products +
                ", finalized=" + finalized +
                '}';
    }
}
