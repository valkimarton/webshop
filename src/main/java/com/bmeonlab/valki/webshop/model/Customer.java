package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @Column(name = "firstname")
    @NotBlank(message = "first name may not be blank")
    @Size(max = 50)
    @JsonProperty("first_name")
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "last name may not be blank")
    @Size(max = 50)
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "dateofbirth")
    @NotNull(message = "date of birth may not be blank")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("date_of_birth")
    private Date dateOfBirth;

    @NotNull(message = "gender may not be null")
    @JsonProperty("gender")
    private String gender;          // TODO: enum legyen

    @Size(max = 100)
    @Email(message = "Invalid e-mail address provided")
    @JsonProperty("email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    @JsonProperty("address")
    private Address address;

    // TODO: Visszarakni ezt a sort: @NotNull(message = "??? (Cart of customer IS NULL)")      // TODO: a kosarat új Customer hozzáadásakor kellene generálni.
    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonProperty("cart")
    private Cart cart;

    public Customer(){}

    public Customer(@JsonProperty("first_name") @NotBlank(message = "first name may not be blank") @Size(max = 50) String firstName,
                    @JsonProperty("last_name") @NotBlank(message = "last name may not be blank") @Size(max = 50) String lastName,
                    @JsonProperty("date_of_birth") Date dateOfBirth,
                    @NotNull(message = "gender may not be null") String gender,
                    @Size(max = 100) @Email(message = "Invalid e-mail address provided") String email,
                    Address address,
                    Cart cart) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.address = address;
        this.cart = cart;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", cart=" + cart +
                '}';
    }
}
