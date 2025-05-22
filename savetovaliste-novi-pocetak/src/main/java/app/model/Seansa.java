package app.model;

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
    private Integer kandidatId; // nullable
    private int osobaId;

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

    public Integer getKandidatId() {
        return kandidatId;
    }

    public void setKandidatId(Integer kandidatId) {
        this.kandidatId = kandidatId;
    }

    public int getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(int osobaId) {
        this.osobaId = osobaId;
    }

    @Override
    public String toString() {
        return "Seansa " + seansaId + " - " + datum;
    }
}
