package app.model;

import java.sql.Date;

public class Prijava {
    private int prijavaId;
    private Date datum;
    private int osobaId;

    public int getPrijavaId() {
        return prijavaId;
    }

    public void setPrijavaId(int prijavaId) {
        this.prijavaId = prijavaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getOsobaId() {
        return osobaId;
    }

    public void setOsobaId(int osobaId) {
        this.osobaId = osobaId;
    }

    @Override
    public String toString() {
        return "Prijava [id=" + prijavaId + ", datum=" + datum + ", osobaId=" + osobaId + "]";
    }
}
