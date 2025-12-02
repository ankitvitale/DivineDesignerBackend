package com.DivineDesignerDen.DTO;

import java.time.LocalDate;
import java.util.List;

public class TailorOrderRequest {

    private String orderNo;
    private String customerName;
    private String mobile;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private double total;
    private double advance;
    private double balance;

    private List<GarmentDTO> garments;

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

    public List<GarmentDTO> getGarments() {
        return garments;
    }

    public void setGarments(List<GarmentDTO> garments) {
        this.garments = garments;
    }
}
