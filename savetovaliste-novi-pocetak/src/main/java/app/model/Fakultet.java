package app.model;

public class Fakultet {
    private int fakultetId;
    private String naziv;
    private int univerzitetId;

    public int getFakultetId() {
        return fakultetId;
    }

    public void setFakultetId(int fakultetId) {
        this.fakultetId = fakultetId;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public int getUniverzitetId() {
        return univerzitetId;
    }

    public void setUniverzitetId(int univerzitetId) {
        this.univerzitetId = univerzitetId;
    }

    @Override
    public String toString() {
        return naziv;
    }
}
