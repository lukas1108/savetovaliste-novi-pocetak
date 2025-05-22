package app.dao;

import app.model.CentarZaObuku;

import java.sql.*;

public class CentarZaObukuDAO {
    private Connection connection;

    public CentarZaObukuDAO(Connection connection) {
        this.connection = connection;
    }

    // Metoda koja trazi centar po nazivu, bez obzira na velika/mala slova
    public CentarZaObuku nadjiCentarPoNazivu(String naziv) throws SQLException {
        String sql = "SELECT * FROM centar_za_obuku WHERE LOWER(naziv) = LOWER(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CentarZaObuku(
                        rs.getInt("centar_id"),
                        rs.getString("naziv"),
                        rs.getString("email"),
                        rs.getString("telefon"),
                        rs.getString("ulica"),
                        rs.getInt("broj"),
                        rs.getString("opstina")
                );
            }
            return null; // nije pronadjen centar
        }
    }

    // Metoda za kreiranje novog centra, vraca ID novog centra
    public int kreirajCentar(CentarZaObuku centar) throws SQLException {
        String sql = "INSERT INTO centar_za_obuku (naziv, email, telefon, ulica, broj, opstina) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, centar.getNaziv());
            ps.setString(2, centar.getEmail());
            ps.setString(3, centar.getTelefon());
            ps.setString(4, centar.getUlica());
            ps.setInt(5, centar.getBroj());
            ps.setString(6, centar.getOpstina());

            ps.executeUpdate();

            ResultSet keys = ps.getGeneratedKeys();
            if (keys.next()) {
                return keys.getInt(1);
            } else {
                throw new SQLException("Neuspesno kreiranje centra, nije dobijen ID");
            }
        }
    }
}

