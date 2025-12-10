package com.DivineDesignerDen.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Measurement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lambai;     // length
    private String pet;        // belly
    private String chati;      // chest
    private String seat;       // seat
    private String shoulder;   // shoulder
    private String asteen;     // sleeve
    private String gala;       // neck
    private String kaf;        // cuff
    private String mohri;      // cuff (kurta)

    // Pant / Pajama fields
    private String kamar;      // waist
    private String jang;       // thigh
    private String latka;      // inseam
    private String bottom;     // bottom
    private String ghutna;     // knee
    @OneToOne
    @JsonBackReference
    private Garment garment;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLambai() {
        return lambai;
    }

    public void setLambai(String lambai) {
        this.lambai = lambai;
    }

    public String getPet() {
        return pet;
    }

    public void setPet(String pet) {
        this.pet = pet;
    }

    public String getChati() {
        return chati;
    }

    public void setChati(String chati) {
        this.chati = chati;
    }

    public String getSeat() {
        return seat;
    }

    public void setSeat(String seat) {
        this.seat = seat;
    }

    public String getShoulder() {
        return shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getAsteen() {
        return asteen;
    }

    public void setAsteen(String asteen) {
        this.asteen = asteen;
    }

    public String getGala() {
        return gala;
    }

    public void setGala(String gala) {
        this.gala = gala;
    }

    public String getKaf() {
        return kaf;
    }

    public void setKaf(String kaf) {
        this.kaf = kaf;
    }

    public String getMohri() {
        return mohri;
    }

    public void setMohri(String mohri) {
        this.mohri = mohri;
    }

    public String getKamar() {
        return kamar;
    }

    public void setKamar(String kamar) {
        this.kamar = kamar;
    }

    public String getJang() {
        return jang;
    }

    public void setJang(String jang) {
        this.jang = jang;
    }

    public String getLatka() {
        return latka;
    }

    public void setLatka(String latka) {
        this.latka = latka;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public String getGhutna() {
        return ghutna;
    }

    public void setGhutna(String ghutna) {
        this.ghutna = ghutna;
    }

    public Garment getGarment() {
        return garment;
    }

    public void setGarment(Garment garment) {
        this.garment = garment;
    }
}
