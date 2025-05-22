package app.model;

import java.sql.Date;

public class CenaSeanse {
    private int cenaId;
    private Date datumPromene;
    private double novaCena;

    public int getCenaId() {
        return cenaId;
    }

    public void setCenaId(int cenaId) {
        this.cenaId = cenaId;
    }

    public Date getDatumPromene() {
        return datumPromene;
    }

    public void setDatumPromene(Date datumPromene) {
        this.datumPromene = datumPromene;
    }

    public double getNovaCena() {
        return novaCena;
    }

    public void setNovaCena(double novaCena) {
        this.novaCena = novaCena;
    }

    @Override
    public String toString() {
        return "CenaSeanse{" +
                "cenaId=" + cenaId +
                ", datumPromene=" + datumPromene +
                ", novaCena=" + novaCena +
                '}';
    }
}
