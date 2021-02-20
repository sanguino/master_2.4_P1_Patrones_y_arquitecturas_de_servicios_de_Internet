package es.sanguino.ecommerce.infrastructure.entity;

import javax.persistence.*;
import java.util.Map;

@Entity
public class CartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    private Map<ProductEntity, Long> products;

    private Boolean finalized;

    public CartEntity() {
    }

    public CartEntity(Map<ProductEntity, Long> products, Boolean finalized) {
        this.products = products;
        this.finalized = finalized;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Map<ProductEntity, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<ProductEntity, Long> products) {
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
        return "CartEntity{" +
                "id=" + id +
                ", products=" + products +
                ", finalized=" + finalized +
                '}';
    }
}
