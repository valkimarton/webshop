package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "address")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(max = 100)
    private String country;

    @Column(name = "zipcode")
    @NotNull(message = "zip code may not be null")
    private int zipCode;

    @NotBlank(message = "town may not be null")
    @Size(max = 100)
    private String town;

    @NotBlank(message = "street may not be null")
    @Size(max = 100)
    private String street;

    @Column(name = "housenumber")
    @NotNull(message = "house number may not be null")
    private int houseNumber;

    protected Address() {}

    public Address(@Size(max = 100) String country,
                   @NotNull(message = "zip code may not be null") int zipCode,
                   @NotBlank(message = "town may not be null") @Size(max = 100) String town,
                   @NotBlank(message = "street may not be null") @Size(max = 100) String street,
                   @NotNull(message = "house number may not be null") int houseNumber) {
        this.country = country;
        this.zipCode = zipCode;
        this.town = town;
        this.street = street;
        this.houseNumber = houseNumber;
    }


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }


    public Long getId() {
        return id;
    }
}
