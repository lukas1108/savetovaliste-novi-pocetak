package app.model;

import java.sql.Date;

public class Kurs {
    private int kursId;
    private Date datum;
    private double kursZaRsd;
    private int valutaId;

    public int getKursId() {
        return kursId;
    }

    public void setKursId(int kursId) {
        this.kursId = kursId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public double getKursZaRsd() {
        return kursZaRsd;
    }

    public void setKursZaRsd(double kursZaRsd) {
        this.kursZaRsd = kursZaRsd;
    }

    public int getValutaId() {
        return valutaId;
    }

    public void setValutaId(int valutaId) {
        this.valutaId = valutaId;
    }

    @Override
    public String toString() {
        return "Kurs na dan " + datum + ": " + kursZaRsd;
    }
}
