package com.example.prospectron.model;

import javax.persistence.*;

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
    @ManyToOne(fetch = FetchType.EAGER)     //define mapping relationship and enable eager loading
    @JoinColumn(name="transaction_id")  //map products to transaction by joining on transaction_id
    private Transaction transaction;


    public Product(){

    }

    public Product(Long id, double price, String name, Transaction transaction) {
        this.id = id;
        this.price = price;
        this.name = name;
        this.transaction = transaction;
    }

    public Product(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public Product(double price, String name, Transaction transaction) {
        this.price = price;
        this.name = name;
        this.transaction = transaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Transaction gettransaction() {
        return transaction;
    }

    public void settransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", transaction=" + transaction +
                '}';
    }
}
