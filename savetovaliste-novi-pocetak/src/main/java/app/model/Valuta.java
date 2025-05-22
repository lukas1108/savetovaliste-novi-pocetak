package app.model;

public class Valuta {
    private int valutaId;
    private String skraceniNaziv;
    private String punNaziv;

    public int getValutaId() {
        return valutaId;
    }

    public void setValutaId(int valutaId) {
        this.valutaId = valutaId;
    }

    public String getSkraceniNaziv() {
        return skraceniNaziv;
    }

    public void setSkraceniNaziv(String skraceniNaziv) {
        this.skraceniNaziv = skraceniNaziv;
    }

    public String getPunNaziv() {
        return punNaziv;
    }

    public void setPunNaziv(String punNaziv) {
        this.punNaziv = punNaziv;
    }

    @Override
    public String toString() {
        return "Valuta{id=" + valutaId + ", skraceniNaziv='" + skraceniNaziv + "', punNaziv='" + punNaziv + "'}";
    }
}
