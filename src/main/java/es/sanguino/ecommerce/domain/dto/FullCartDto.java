package es.sanguino.ecommerce.domain.dto;

import java.util.HashMap;
import java.util.Map;

public class FullCartDto {

    private Long id;
    private Map<FullProductDto, Long> products;
    private Boolean finalized;

    public FullCartDto() {
    }

    public FullCartDto(Long id, Map<FullProductDto, Long> products, Boolean finalized) {
        this.id = id;
        this.products = products;
        this.finalized = finalized;
    }

    public FullCartDto(Map<FullProductDto, Long> products, Boolean finalized) {
        this(null, products, finalized);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", products=" + products +
                ", finalized=" + finalized +
                '}';
    }
}
