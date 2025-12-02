package com.DivineDesignerDen.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class PaymentHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private TailorOrder order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TailorOrder getOrder() {
        return order;
    }

    public void setOrder(TailorOrder order) {
        this.order = order;
    }
}
