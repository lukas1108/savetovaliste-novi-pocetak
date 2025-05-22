package app.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;

public class Placanje {
    private int placanjeId;
    private String svrha;
    private int brojRata;
    private boolean jePrvaRata;
    private Date rokZaDruguRatu;  // moze biti null
    private String nacinPlacanja;
    private BigDecimal iznos;
    private Timestamp datumUplate;
    private BigDecimal provizija;  // moze biti null
    private int valutaId;
    private int seansaId;

    public int getPlacanjeId() {
        return placanjeId;
    }

    public void setPlacanjeId(int placanjeId) {
        this.placanjeId = placanjeId;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public int getBrojRata() {
        return brojRata;
    }

    public void setBrojRata(int brojRata) {
        this.brojRata = brojRata;
    }

    public boolean isJePrvaRata() {
        return jePrvaRata;
    }

    public void setJePrvaRata(boolean jePrvaRata) {
        this.jePrvaRata = jePrvaRata;
    }

    public Date getRokZaDruguRatu() {
        return rokZaDruguRatu;
    }

    public void setRokZaDruguRatu(Date rokZaDruguRatu) {
        this.rokZaDruguRatu = rokZaDruguRatu;
    }

    public String getNacinPlacanja() {
        return nacinPlacanja;
    }

    public void setNacinPlacanja(String nacinPlacanja) {
        this.nacinPlacanja = nacinPlacanja;
    }

    public BigDecimal getIznos() {
        return iznos;
    }

    public void setIznos(BigDecimal iznos) {
        this.iznos = iznos;
    }

    public Timestamp getDatumUplate() {
        return datumUplate;
    }

    public void setDatumUplate(Timestamp datumUplate) {
        this.datumUplate = datumUplate;
    }

    public BigDecimal getProvizija() {
        return provizija;
    }

    public void setProvizija(BigDecimal provizija) {
        this.provizija = provizija;
    }

    public int getValutaId() {
        return valutaId;
    }

    public void setValutaId(int valutaId) {
        this.valutaId = valutaId;
    }

    public int getSeansaId() {
        return seansaId;
    }

    public void setSeansaId(int seansaId) {
        this.seansaId = seansaId;
    }

    @Override
    public String toString() {
        return "Placanje [id=" + placanjeId + ", svrha=" + svrha + ", iznos=" + iznos + "]";
    }
}
