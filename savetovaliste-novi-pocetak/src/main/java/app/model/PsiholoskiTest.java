package app.model;

import java.math.BigDecimal;

public class PsiholoskiTest {
    private int testId;
    private String naziv;
    private String oblast;
    private BigDecimal cena;
    private String rezultat;
    private int seansaId;

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOblast() {
        return oblast;
    }

    public void setOblast(String oblast) {
        this.oblast = oblast;
    }

    public BigDecimal getCena() {
        return cena;
    }

    public void setCena(BigDecimal cena) {
        this.cena = cena;
    }

    public String getRezultat() {
        return rezultat;
    }

    public void setRezultat(String rezultat) {
        this.rezultat = rezultat;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public void setSeansaId(int seansaId) {
        this.seansaId = seansaId;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
