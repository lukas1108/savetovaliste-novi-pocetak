package app.model;

public class Univerzitet {
    private int univerzitetId;
    private String naziv;
    private int usmerenjeId;

    public int getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(int univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getUsmerenjeId() {
        return usmerenjeId;
    }

    public void setUsmerenjeId(int usmerenjeId) {
        this.usmerenjeId = usmerenjeId;
    }

    @Override
    public String toString() {
        return "Univerzitet{id=" + univerzitetId + ", naziv='" + naziv + "'}";
    }
}
