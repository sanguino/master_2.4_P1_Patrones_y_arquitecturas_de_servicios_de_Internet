package es.sanguino.ecommerce.domain;

public class ProductDto {

    private String name;
    private long price;

    public ProductDto() {

    }

    public ProductDto(String name, long price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductDto{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
