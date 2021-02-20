package es.sanguino.ecommerce.domain.dto;

public class FullProductDto {

    private Long id;
    private String name;
    private Double price;

    public FullProductDto() {
    }

    public FullProductDto(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public FullProductDto(String name, Double price) {
        this(null, name, price);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ProductResponseDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
