package com.example.prospectron.model;

import com.fasterxml.jackson.databind.util.JSONPObject;

import javax.persistence.*;
import java.math.BigDecimal;
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

    private Long id;
    private Timestamp date;
    private double saleAmount;
    private String orderRef;
    private String productIds;

    public Transaction(){

    }

    public Transaction(Long id,
                       Timestamp date,
                       double saleAmount,
                       String orderRef,
                       String productIds) {
        this.id = id;
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.productIds = productIds;
    }

    public Transaction(Timestamp date, double saleAmount, String orderRef, String productIds) {
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.productIds = productIds;
    }


    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
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

    public String getProductIds() {
        return productIds;
    }

    public void setProductIds(String productIds) {
        this.productIds = productIds;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", date=" + date +
                ", productIds=" + productIds +
                '}';
    }
}
