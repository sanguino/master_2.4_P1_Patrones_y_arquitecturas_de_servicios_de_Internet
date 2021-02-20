package es.sanguino.ecommerce.domain.dto;

import java.util.Map;

public class CartDto {


    private Map<FullProductDto, Long> products;
    private Boolean finalized;

    public CartDto() {
    }

    public CartDto(Map<FullProductDto, Long> products, Boolean finalized) {
        this.products = products;
        this.finalized = finalized;
    }

    public Map<FullProductDto, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<FullProductDto, Long> products) {
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
