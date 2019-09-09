package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "product name may not be blank")
    @Size(min = 2, max = 100)
    private String name;

    @NotNull(message = "product price may not be null")
    @Max(1000000000)
    @PositiveOrZero
    private int price;

    @NotBlank(message = "product category may not be blank")
    @Size(max = 30)
    private String category;

    @NotBlank(message = "product color may not be blank")
    private String color;           // TODO: change to enum

    @ManyToOne
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    @OneToMany(targetEntity = Review.class, mappedBy = "product")      // TODO: Cascade property-nek utánanézni.
    private List<Review> reviews;




    // TODO: Kell ez a default konstruktor?
    protected Product() {}

    // TODO: Konstruktorban kellenek a validációs annotációk?  Ha igen, rissíteni annotációkkal
    public Product(@NotBlank @Size(min = 2, max = 100) String name, int price, String category, String color, Manufacturer manufacturer, List<Review> reviews) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.color = color;
        this.manufacturer = manufacturer;
        this.reviews = reviews;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }


}
