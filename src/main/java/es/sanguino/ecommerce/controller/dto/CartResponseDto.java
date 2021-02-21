package es.sanguino.ecommerce.controller.dto;


import java.util.Map;

public class CartResponseDto {

    private Long id;
    private Map<ProductResponseDto, Long> products;
    private Boolean finalized;

    public CartResponseDto() {
    }

    public CartResponseDto(Long id, Map<ProductResponseDto, Long> products, Boolean finalized) {
        this.id = id;
        this.products = products;
        this.finalized = finalized;
    }

    public CartResponseDto(Map<ProductResponseDto, Long> products, Boolean finalized) {
        this(null, products, finalized);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<ProductResponseDto, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductResponseDto, Long> products) {
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
