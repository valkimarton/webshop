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

    @NotBlank(message = "name may not be blank")
    @Size(max = 100)
    private String name;

    @Column(unique = true)
    @NotBlank(message = "username may not be blank")
    @Size(max = 50)
    private String username;

    // @JsonIgnore      // TODO: how to solve this: If ignored, can't create user
    @JsonProperty("password")
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
    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateOfBirth;

    private String gender;          // TODO: enum legyen

    @Size(max = 100)
    @Email(message = "Invalid e-mail address provided")
    private String email;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cart_id")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private List<Invoice> invoices;

    public Customer(){}

    public Customer(@NotBlank(message = "name may not be blank") @Size(max = 100) String name,
                    @NotBlank(message = "username may not be blank") @Size(max = 50) String username,
                    @NotBlank(message = "password may not be blank") @Size(max = 1000) String password,
                    List<Role> roles,
                    @NotNull(message = "date of birth may not be blank") Date dateOfBirth,
                    @NotNull(message = "gender may not be null") String gender,
                    @Size(max = 100) @Email(message = "Invalid e-mail address provided") String email,
                    Address address) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.email = email;
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", cart=" + cart +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) { this.roles.add(role); }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
