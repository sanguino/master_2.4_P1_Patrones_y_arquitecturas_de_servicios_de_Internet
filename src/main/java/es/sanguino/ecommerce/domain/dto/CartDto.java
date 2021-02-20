package es.sanguino.ecommerce.domain.dto;

import java.util.HashMap;

public class CartDto {


    private HashMap<FullProductDto, Long> products;
    private Boolean finalized = false;

    public CartDto() {
    }

    public CartDto(HashMap<FullProductDto, Long> products, Boolean finalized) {
        this.products = products;
        this.finalized = finalized;
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
                ", products=" + products +
                ", finalized=" + finalized +
                '}';
    }
}
