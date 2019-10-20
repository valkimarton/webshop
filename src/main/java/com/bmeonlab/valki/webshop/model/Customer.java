package com.bmeonlab.valki.webshop.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "cart" })
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    @NotBlank(message = "first name may not be blank")
    @Size(max = 50)
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "last name may not be blank")
    @Size(max = 50)
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "username may not be blank")
    @Size(max = 50)
    private String username;

    @NotBlank(message = "password may not be blank")
    @Size(max=1000)
    private String password;

    @NotNull(message = "enabled may not be null")
    private boolean enabled;

    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;

    @Column(name = "dateofbirth")
    @NotNull(message = "date of birth may not be blank")
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    @NotNull(message = "gender may not be null")
    private String gender;          // TODO: enum legyen

    @Size(max = 100)
    @Email(message = "Invalid e-mail address provided")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    // TODO: Visszarakni ezt a sort: @NotNull(message = "??? (Cart of customer IS NULL)")      // TODO: a kosarat új Customer hozzáadásakor kellene generálni.
    @OneToOne
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    public Customer(){}

    public Customer(@NotBlank(message = "first name may not be blank") @Size(max = 50) String firstName,
                    @NotBlank(message = "last name may not be blank") @Size(max = 50) String lastName,
                    Date dateOfBirth,
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public Cart getCart() {
        return cart;
    }
}
