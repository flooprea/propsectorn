package com.example.prospectron.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table
public class Transaction {
    @Id
    @SequenceGenerator(
            name = "transaction_sequence",
            sequenceName = "transaction_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "transaction_sequence"
    )

    @Column(name = "id")
    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private double saleAmount;
    private String orderRef;
    @Transient  //eliminates the need to have this information persisted from the db
    @JsonBackReference   //avoid JSON infinite recursion exception
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "transaction")   //map entities and enable eager loading
    private List<Product> products;

    public Transaction(){
    }

    public Transaction(Long id,
                       Timestamp date,
                       double saleAmount,
                       String orderRef,
                       List<Product> products) {
        this.id = id;
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.products = products;
    }

    public Transaction(Timestamp date,
                       double saleAmount,
                       String orderRef,
                       List<Product> products) {
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.products = products;
    }

    public Transaction(Timestamp date,String orderRef) {
        this.date = date;
        this.orderRef = orderRef;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getDate() {
        return date;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", saleAmount=" + saleAmount +
                ", orderRef='" + orderRef + '\'' +
                ", products=" + products +
                '}';
    }
}
