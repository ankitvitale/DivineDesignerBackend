package com.DivineDesignerDen.DTO;

public class GarmentDTO {
    private String type;
    private Integer quantity;
    private MeasurementDTO measurement;

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

    public MeasurementDTO getMeasurement() {
        return measurement;
    }

    public void setMeasurement(MeasurementDTO measurement) {
        this.measurement = measurement;
    }
}
