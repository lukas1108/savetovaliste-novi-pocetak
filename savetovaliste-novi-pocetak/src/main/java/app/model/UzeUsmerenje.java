package app.model;

public class UzeUsmerenje {
    private int usmerenjeId;
    private String naziv;

    public int getUsmerenjeId() {
        return usmerenjeId;
    }

    public void setUsmerenjeId(int usmerenjeId) {
        this.usmerenjeId = usmerenjeId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public String toString() {
        return "Usmerenje{id=" + usmerenjeId + ", naziv='" + naziv + "'}";
    }
}
