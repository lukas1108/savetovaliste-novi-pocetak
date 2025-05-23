package app.model;

import java.sql.Date;

public class Osoba {
    private int id;
    private String ime;
    private String prezime;
    private String jmbg;
    private Date datumRodjenja;
    private String pol;
    private String email;
    private String telefon;
    private String ulica;
    private int broj;
    private String opstina;
    private String stepenStudija;
    private Date datumSertifikacije;
    private Integer centarId;         // promenjeno u Integer
    private int fakultetId;
    private int univerzitetId;
    private Integer supervizijaId;    // promenjeno u Integer
    private String tipOsobe;
    private Integer oblastId;         // promenjeno u Integer
    private String oblastNaziv;       // dodat naziv oblasti
    private String lozinka;

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }
// getteri i setteri za sve

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
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

    public String getUlica() {
        return ulica;
    }

    public void setUlica(String ulica) {
        this.ulica = ulica;
    }

    public int getBroj() {
        return broj;
    }

    public void setBroj(int broj) {
        this.broj = broj;
    }

    public String getOpstina() {
        return opstina;
    }

    public void setOpstina(String opstina) {
        this.opstina = opstina;
    }

    public String getStepenStudija() {
        return stepenStudija;
    }

    public void setStepenStudija(String stepenStudija) {
        this.stepenStudija = stepenStudija;
    }

    public Date getDatumSertifikacije() {
        return datumSertifikacije;
    }

    public void setDatumSertifikacije(Date datumSertifikacije) {
        this.datumSertifikacije = datumSertifikacije;
    }

    public Integer getCentarId() {
        return centarId;
    }

    public void setCentarId(Integer centarId) {
        this.centarId = centarId;
    }

    public int getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(int fakultetId) {
        this.fakultetId = fakultetId;
    }

    public int getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(int univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    public Integer getSupervizijaId() {
        return supervizijaId;
    }

    public void setSupervizijaId(Integer supervizijaId) {
        this.supervizijaId = supervizijaId;
    }

    public String getTipOsobe() {
        return tipOsobe;
    }

    public void setTipOsobe(String tipOsobe) {
        this.tipOsobe = tipOsobe;
    }

    public Integer getOblastId() {
        return oblastId;
    }

    public void setOblastId(Integer oblastId) {
        this.oblastId = oblastId;
    }

    public String getOblastNaziv() {
        return oblastNaziv;
    }

    public void setOblastNaziv(String oblastNaziv) {
        this.oblastNaziv = oblastNaziv;
    }

    @Override
    public String toString() {
        return ime + " " + prezime + " – " + oblastNaziv + " (" + telefon + " | " + email + ")";
    }
}
