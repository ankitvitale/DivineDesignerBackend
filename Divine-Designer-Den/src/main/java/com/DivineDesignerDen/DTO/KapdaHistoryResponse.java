package com.DivineDesignerDen.DTO;


import java.time.LocalDate;
import java.util.List;

public class KapdaHistoryResponse {
    private Long kapdaId;
    private String barcode;
    private String name;
    private String type;
    private String color;
    private String unit;
    private Double pricePerUnit;
    private String supplier;
    private LocalDate dateAdded;
    private Double totalIn;
    private Double totalOut;
    private Double currentStock;
    private List<KapdaTransactionDTO> history;

    public Long getKapdaId() {
        return kapdaId;
    }

    public void setKapdaId(Long kapdaId) {
        this.kapdaId = kapdaId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(Double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public LocalDate getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(LocalDate dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Double getTotalIn() {
        return totalIn;
    }

    public void setTotalIn(Double totalIn) {
        this.totalIn = totalIn;
    }

    public Double getTotalOut() {
        return totalOut;
    }

    public void setTotalOut(Double totalOut) {
        this.totalOut = totalOut;
    }

    public Double getCurrentStock() {
        return currentStock;
    }

    public void setCurrentStock(Double currentStock) {
        this.currentStock = currentStock;
    }

    public List<KapdaTransactionDTO> getHistory() {
        return history;
    }

    public void setHistory(List<KapdaTransactionDTO> history) {
        this.history = history;
    }
}
