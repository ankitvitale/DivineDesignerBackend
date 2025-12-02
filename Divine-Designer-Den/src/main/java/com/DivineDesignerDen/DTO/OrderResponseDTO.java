package com.DivineDesignerDen.DTO;

import java.time.LocalDate;
import java.util.List;

public class OrderResponseDTO {

    private Long id;
    private String orderNo;
    private String customerName;
    private String mobile;
    private LocalDate orderDate;
    private LocalDate deliveryDate;

    private Double total;
    private Double advance;
    private Double balance;


    private List<PaymentHistoryDTO> paymentHistory;

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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<PaymentHistoryDTO> getPaymentHistory() {
        return paymentHistory;
    }

    public void setPaymentHistory(List<PaymentHistoryDTO> paymentHistory) {
        this.paymentHistory = paymentHistory;
    }
}
