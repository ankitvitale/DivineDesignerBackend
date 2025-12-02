package com.DivineDesignerDen.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class StockHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private String type;   // IN or OUT
    private double quantity;
    private String note;

    @ManyToOne
    @JoinColumn(name = "kapda_id")
    private Kapda kapda;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Kapda getKapda() {
        return kapda;
    }

    public void setKapda(Kapda kapda) {
        this.kapda = kapda;
    }
}
