package com.DivineDesignerDen.Entity;


import jakarta.persistence.*;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lengthSize;
    private String chestSize;
    private String waistSize;
    private String seatSize;
    private String shoulderSize;
    private String sleeveSize;
    private String neckSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLengthSize() {
        return lengthSize;
    }

    public void setLengthSize(String lengthSize) {
        this.lengthSize = lengthSize;
    }

    public String getChestSize() {
        return chestSize;
    }

    public void setChestSize(String chestSize) {
        this.chestSize = chestSize;
    }

    public String getWaistSize() {
        return waistSize;
    }

    public void setWaistSize(String waistSize) {
        this.waistSize = waistSize;
    }

    public String getSeatSize() {
        return seatSize;
    }

    public void setSeatSize(String seatSize) {
        this.seatSize = seatSize;
    }

    public String getShoulderSize() {
        return shoulderSize;
    }

    public void setShoulderSize(String shoulderSize) {
        this.shoulderSize = shoulderSize;
    }

    public String getSleeveSize() {
        return sleeveSize;
    }

    public void setSleeveSize(String sleeveSize) {
        this.sleeveSize = sleeveSize;
    }

    public String getNeckSize() {
        return neckSize;
    }

    public void setNeckSize(String neckSize) {
        this.neckSize = neckSize;
    }
}
