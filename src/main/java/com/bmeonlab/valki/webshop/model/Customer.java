package com.bmeonlab.valki.webshop.model;

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
    @NotBlank(message = "first name may not be blank")
    @Size(max = 50)
    private String firstName;

    @Column(name = "lastname")
    @NotBlank(message = "last name may not be blank")
    @Size(max = 50)
    private String lastName;

    @Column(name = "dateofbirth")
    @NotBlank(message = "date of birth may not be blank")
    @Temporal(TemporalType.DATE)
    private Date dateOfBirth;

    @NotNull(message = "gender may not be null")
    private String gender;          // TODO: enum legyen

    @Size(max = 100)
    @Email(message = "Invalid e-mail address provided")
    private String email;

    @ManyToOne
    private Address address;

    @NotNull(message = "??? (Cart of customer IS NULL)")      // TODO: a kosarat új Customer hozzáadásakor kellene generálni.
    @OneToOne
    private Cart cart;
}
