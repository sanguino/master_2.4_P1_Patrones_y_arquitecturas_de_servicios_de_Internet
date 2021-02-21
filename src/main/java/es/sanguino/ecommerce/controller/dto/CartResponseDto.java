package es.sanguino.ecommerce.controller.dto;


import java.util.List;

public class CartResponseDto {

    private Long id;
    private List<ProductQuantityResponseDto> products;
    private Boolean finalized;

    public CartResponseDto() {
    }

    public CartResponseDto(Long id, List<ProductQuantityResponseDto> products, Boolean finalized) {
        this.id = id;
        this.products = products;
        this.finalized = finalized;
    }

    public CartResponseDto(List<ProductQuantityResponseDto> products, Boolean finalized) {
        this(null, products, finalized);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductQuantityResponseDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductQuantityResponseDto> products) {
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
