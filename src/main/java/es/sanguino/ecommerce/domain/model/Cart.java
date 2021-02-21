package es.sanguino.ecommerce.domain.model;

import java.util.HashMap;
import java.util.Map;

public class Cart {


    private Map<Product, Long> products;
    private Boolean finalized;

    public Cart() {
        this.finalized = false;
        this.products = new HashMap<>();
    }

    public Cart(Map<Product, Long> products, Boolean finalized) {
        this.products = products;
        this.finalized = finalized;
    }

    public Map<Product, Long> getProducts() {
        return products;
    }

    public void setProducts(Map<Product, Long> products) {
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
