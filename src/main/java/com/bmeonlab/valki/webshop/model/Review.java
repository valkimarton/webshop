package com.bmeonlab.valki.webshop.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "customer may not be empty")
    @ManyToOne
    private Customer customer;

    @NotNull(message = "product may not be empty")
    @ManyToOne
    private Product product;

    @Range(min = 1, max = 10)
    @NotNull(message = "grade may not be empty")
    private int grade;

    @Size(max = 1000)
    private String content;

    @NotNull(message = "date may not be null")
    @Temporal(TemporalType.DATE)        // TODO: Ez legyen DateTime
    private Date date;

    protected Review() {}

    public Review(@NotNull(message = "customer may not be empty") Customer customer,
                  @NotNull(message = "product may not be empty") Product product,
                  @Range(min = 1, max = 10) @NotNull(message = "grade may not be empty") int grade,
                  @Size(max = 1000) String content) {
        this.customer = customer;
        this.product = product;
        this.grade = grade;
        this.content = content;

        this.date = new Date();     // a létrehozás dátumát tartalmazza
    }
}
