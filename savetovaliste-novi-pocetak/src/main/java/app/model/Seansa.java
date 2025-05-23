package app.model;

import app.dao.KlijentDAO;

import java.sql.Time;
import java.sql.Date;

public class Seansa {
    private int seansaId;
    private Date datum;
    private Time vreme;
    private int trajanjeMin;
    private String beleske;
    private int cenaId;
    private Integer objavaId;   // nullable
    private int osobaId;
    private Klijent klijent;   // novo polje za klijenta

    public int getSeansaId() {
        return seansaId;
    }

    public void setSeansaId(int seansaId) {
        this.seansaId = seansaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getVreme() {
        return vreme;
    }

    public void setVreme(Time vreme) {
        this.vreme = vreme;
    }

    public int getTrajanjeMin() {
        return trajanjeMin;
    }

    public void setTrajanjeMin(int trajanjeMin) {
        this.trajanjeMin = trajanjeMin;
    }

    public String getBeleske() {
        return beleske;
    }

    public void setBeleske(String beleske) {
        this.beleske = beleske;
    }

    public int getCenaId() {
        return cenaId;
    }

    public void setCenaId(int cenaId) {
        this.cenaId = cenaId;
    }

    public Integer getObjavaId() {
        return objavaId;
    }

    public void setObjavaId(Integer objavaId) {
        this.objavaId = objavaId;
    }

    public int getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(int osobaId) {
        this.osobaId = osobaId;
    }

    public Klijent getKlijent() {
        return klijent;
    }

    public void setKlijent(Klijent klijent) {
        this.klijent = klijent;
    }

    @Override
    public String toString() {
        return "Seansa " + seansaId + ", " + klijent.getIme() + " " + klijent.getPrezime() + " (" + datum + ")";
    }
}
