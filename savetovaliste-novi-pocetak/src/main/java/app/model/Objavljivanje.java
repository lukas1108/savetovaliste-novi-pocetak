package app.model;

import java.sql.Date;

public class Objavljivanje {
    private int objavaId;
    private Date datum;
    private String primalac;
    private String razlogObjave;

    public int getObjavaId() {
        return objavaId;
    }

    public void setObjavaId(int objavaId) {
        this.objavaId = objavaId;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public String getPrimalac() {
        return primalac;
    }

    public void setPrimalac(String primalac) {
        this.primalac = primalac;
    }

    public String getRazlogObjave() {
        return razlogObjave;
    }

    public void setRazlogObjave(String razlogObjave) {
        this.razlogObjave = razlogObjave;
    }

    @Override
    public String toString() {
        return "Objava za: " + primalac + " dana " + datum;
    }
}
