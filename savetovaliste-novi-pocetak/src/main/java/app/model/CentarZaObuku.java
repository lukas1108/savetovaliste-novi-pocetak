package app.model;

public class CentarZaObuku {
    private int centarId;
    private String naziv;
    private String email;
    private String telefon;
    private String ulica;
    private int broj;
    private String opstina;

    // konstruktor
    public CentarZaObuku(int centarId, String naziv, String email, String telefon,
                         String ulica, int broj, String opstina) {
        this.centarId = centarId;
        this.naziv = naziv;
        this.email = email;
        this.telefon = telefon;
        this.ulica = ulica;
        this.broj = broj;
        this.opstina = opstina;
    }

    // getteri i setteri
    public int getCentarId() { return centarId; }
    public void setCentarId(int centarId) { this.centarId = centarId; }

    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefon() { return telefon; }
    public void setTelefon(String telefon) { this.telefon = telefon; }

    public String getUlica() { return ulica; }
    public void setUlica(String ulica) { this.ulica = ulica; }

    public int getBroj() { return broj; }
    public void setBroj(int broj) { this.broj = broj; }

    public String getOpstina() { return opstina; }
    public void setOpstina(String opstina) { this.opstina = opstina; }
}

