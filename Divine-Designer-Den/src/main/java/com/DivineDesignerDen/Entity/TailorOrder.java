package com.DivineDesignerDen.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class TailorOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String orderNo;
    private String customerName;
    private String mobile;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private double total;
    private double advance;
    private double balance;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Garment> garments;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @JsonIgnore   // prevents infinite loop
    private List<PaymentHistory> paymentHistoryList = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getAdvance() {
        return advance;
    }

    public void setAdvance(double advance) {
        this.advance = advance;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Garment> getGarments() {
        return garments;
    }

    public void setGarments(List<Garment> garments) {
        this.garments = garments;
    }

    public List<PaymentHistory> getPaymentHistoryList() {
        return paymentHistoryList;
    }

    public void setPaymentHistoryList(List<PaymentHistory> paymentHistoryList) {
        this.paymentHistoryList = paymentHistoryList;
    }
}
