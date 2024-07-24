package hng_java_boilerplate.product.dto;

public class ProductListDTO {
    private String name;
    private float price; // Make sure this is a float
    private String category;

    public ProductDTO(String name, float price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    // Getters and Setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}