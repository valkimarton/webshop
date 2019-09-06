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
    private Long id;

    @Column(name = "firstname")
    //@NotBlank(message = "first name may not be blank")
    @Size(max = 50)
    private String firstName;

    @Column(name = "lastname")
    //@NotBlank(message = "last name may not be blank")
    @Size(max = 50)
    private String lastName;

    @Column(name = "dateofbirth")
    @NotNull(message = "date of birth may not be blank")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    //@NotNull(message = "gender may not be null")
    private String gender;          // TODO: enum legyen

    @Size(max = 100)
    @Email(message = "Invalid e-mail address provided")
    private String email;

    @ManyToOne
    private Address address;

    // TODO: Visszarakni ezt a sort: @NotNull(message = "??? (Cart of customer IS NULL)")      // TODO: a kosarat új Customer hozzáadásakor kellene generálni.
    @OneToOne
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
