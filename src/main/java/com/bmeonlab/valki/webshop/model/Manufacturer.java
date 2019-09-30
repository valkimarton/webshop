package com.bmeonlab.valki.webshop.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "manufacturer")
public class Manufacturer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotBlank(message = "town may not be blank")
    @Size(max = 100)
    private String name;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "dateoffundation")
    @Temporal(TemporalType.DATE)
    private Date dateOfFoundation;

    @Size(max = 1000)
    private String description;

    protected Manufacturer() {}

    public Manufacturer(@NotBlank(message = "town may not be blank") @Size(max = 100) String name,
                        Address address,
                        Date dateOfFoundation,
                        @Size(max = 1000) String description) {
        this.name = name;
        this.address = address;
        this.dateOfFoundation = dateOfFoundation;
        this.description = description;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Date getDateOfFoundation() {
        return dateOfFoundation;
    }

    public void setDateOfFoundation(Date dateOfFoundation) {
        this.dateOfFoundation = dateOfFoundation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}
