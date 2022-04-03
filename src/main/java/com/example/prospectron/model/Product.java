package com.example.prospectron.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Product {
    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;
    private double price;
    private String name;
    private Long transactionId;

    public Product(){

    }

    public Product(Long id, double price, String name, Long transactionId) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.transactionId = transactionId;
    }

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product(double price, String name, Long transactionId) {
        this.price = price;
        this.name = name;
        this.transactionId = transactionId;
    }

    public Long getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }
}
