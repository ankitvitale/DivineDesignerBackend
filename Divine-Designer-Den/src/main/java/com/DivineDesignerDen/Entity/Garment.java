package com.DivineDesignerDen.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class Garment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;   // Coat, Jodhpur, Pant, Shirt etc.

    private Integer quantity;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    private Measurement measurement;

    @ManyToOne
    @JsonBackReference
    private TailorOrder order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

    public void setMeasurement(Measurement measurement) {
        this.measurement = measurement;
    }

    public TailorOrder getOrder() {
        return order;
    }

    public void setOrder(TailorOrder order) {
        this.order = order;
    }
}
