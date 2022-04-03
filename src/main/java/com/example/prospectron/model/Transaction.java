package com.example.prospectron.transaction;

import com.fasterxml.jackson.databind.util.JSONPObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

public class Transaction {

    private Long id;
    private Timestamp date;
    private BigDecimal saleAmount;
    private String orderRef;
    private List<Long> productIds;

    public Transaction(Timestamp date, BigDecimal saleAmount, String orderRef, List<Long> productIds) {
        this.date = date;
        this.saleAmount = saleAmount;
        this.orderRef = orderRef;
        this.productIds = productIds;
    }

    public Transaction(Timestamp date, String orderRef, List<Long> productIds) {
        this.date = date;
        this.orderRef = orderRef;
        this.productIds = productIds;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public BigDecimal getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(BigDecimal saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getOrderRef() {
        return orderRef;
    }

    public void setOrderRef(String orderRef) {
        this.orderRef = orderRef;
    }

    public List<Long> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Long> productIds) {
        this.productIds = productIds;
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
