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
    private boolean up1=false;
    private boolean up2=false;
    private boolean up3=false;
    private boolean up4=false;

    private boolean down1=false;
    private boolean down2=false;
    private boolean down3=false;
    private boolean down4=false;

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

    public boolean isUp1() {
        return up1;
    }

    public void setUp1(boolean up1) {
        this.up1 = up1;
    }

    public boolean isUp2() {
        return up2;
    }

    public void setUp2(boolean up2) {
        this.up2 = up2;
    }

    public boolean isUp3() {
        return up3;
    }

    public void setUp3(boolean up3) {
        this.up3 = up3;
    }

    public boolean isUp4() {
        return up4;
    }

    public void setUp4(boolean up4) {
        this.up4 = up4;
    }

    public boolean isDown1() {
        return down1;
    }

    public void setDown1(boolean down1) {
        this.down1 = down1;
    }

    public boolean isDown2() {
        return down2;
    }

    public void setDown2(boolean down2) {
        this.down2 = down2;
    }

    public boolean isDown3() {
        return down3;
    }

    public void setDown3(boolean down3) {
        this.down3 = down3;
    }

    public boolean isDown4() {
        return down4;
    }

    public void setDown4(boolean down4) {
        this.down4 = down4;
    }
}
