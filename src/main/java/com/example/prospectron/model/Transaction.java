package com.example.prospectron.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.json.JSONArray;

import javax.persistence.*;
import java.sql.Timestamp;

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

    private Long id;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Timestamp date;
    private double saleAmount;
    private String orderRef;
    @Transient
    private JSONArray products;

    public Transaction(){

    }

    public Transaction(Long id,
                       Timestamp date,
                       double saleAmount,
                       String orderRef,
                       JSONArray products) {
        this.id = id;
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.products = products;
    }

    public Transaction(Timestamp date, double saleAmount, String orderRef, JSONArray products) {
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.products = products;
    }

    public Transaction(Timestamp date,String orderRef) {
        this.date = date;
        this.orderRef = orderRef;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public Long getId() {
        return id;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", products=" + products +
                '}';
    }


}
