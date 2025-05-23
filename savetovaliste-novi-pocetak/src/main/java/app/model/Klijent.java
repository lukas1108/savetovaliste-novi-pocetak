package app.model;

import java.sql.Date;

public class Klijent {
    private int klijentId;
    private String ime;
    private String prezime;
    private Date datumRodjenja;
    private String pol;
    private String email;
    private String telefon;
    private String ranijeIsao;
    private String opisProblema;
    private int prijavaId;

    public int getKlijentId() {
        return klijentId;
    }

    public void setKlijentId(int klijentId) {
        this.klijentId = klijentId;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    public String getPol() {
        return pol;
    }

    public void setPol(String pol) {
        this.pol = pol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getRanijeIsao() {
        return ranijeIsao;
    }

    public void setRanijeIsao(String ranijeIsao) {
        this.ranijeIsao = ranijeIsao;
    }

    public String getOpisProblema() {
        return opisProblema;
    }

    public void setOpisProblema(String opisProblema) {
        this.opisProblema = opisProblema;
    }

    public int getPrijavaId() {
        return prijavaId;
    }

    public void setPrijavaId(int prijavaId) {
        this.prijavaId = prijavaId;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " – " + opisProblema + " (" + telefon + " | " + email + ")";
    }
}
