package app.model;

import java.sql.Date;

public class Supervizija {
    private int supervizijaId;
    private Date pocetak;
    private Date kraj;
    private int psihoterapeutId;
    private int kandidatId;

    public int getSupervizijaId() {
        return supervizijaId;
    }

    public void setSupervizijaId(int supervizijaId) {
        this.supervizijaId = supervizijaId;
    }

    public Date getPocetak() {
        return pocetak;
    }

    public void setPocetak(Date pocetak) {
        this.pocetak = pocetak;
    }

    public Date getKraj() {
        return kraj;
    }

    public void setKraj(Date kraj) {
        this.kraj = kraj;
    }

    public int getPsihoterapeutId() {
        return psihoterapeutId;
    }

    public void setPsihoterapeutId(int psihoterapeutId) {
        this.psihoterapeutId = psihoterapeutId;
    }

    public int getKandidatId() {
        return kandidatId;
    }

    public void setKandidatId(int kandidatId) {
        this.kandidatId = kandidatId;
    }

    @Override
    public String toString() {
        return "Supervizija " + supervizijaId + ": " + pocetak + " - " + kraj;
    }
}
