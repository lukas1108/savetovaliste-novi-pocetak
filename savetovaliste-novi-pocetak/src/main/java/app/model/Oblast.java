package app.model;

public class Oblast {
    private int id;
    private String naziv;
    private int usmerenjeId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        return naziv;
    }
}
