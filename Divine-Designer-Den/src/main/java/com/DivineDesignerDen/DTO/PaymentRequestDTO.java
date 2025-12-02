package com.DivineDesignerDen.DTO;

public class PaymentRequestDTO {
    private Double amount;
    private String method;  // Cash / UPI / Card

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
