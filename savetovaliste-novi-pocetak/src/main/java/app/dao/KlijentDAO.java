package app.dao;

import app.start.DatabaseConnection;
import app.model.Klijent;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KlijentDAO {

    public Klijent getById(int id) {
        String sql = "SELECT * FROM klijent WHERE klijent_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Klijent k = new Klijent();
                k.setKlijentId(rs.getInt("klijent_id"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setDatumRodjenja(rs.getDate("datum_rodjenja"));
                k.setPol(rs.getString("pol"));
                k.setEmail(rs.getString("email"));
                k.setTelefon(rs.getString("telefon"));
                k.setRanijeIsao(rs.getString("ranije_isao"));
                k.setOpisProblema(rs.getString("opis_problema"));
                k.setPrijavaId(rs.getInt("prijava_id"));
                return k;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Klijent> getAll() {
        List<Klijent> lista = new ArrayList<>();
        String sql = "SELECT * FROM klijent";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Klijent k = new Klijent();
                k.setKlijentId(rs.getInt("klijent_id"));
                k.setIme(rs.getString("ime"));
                k.setPrezime(rs.getString("prezime"));
                k.setDatumRodjenja(rs.getDate("datum_rodjenja"));
                k.setPol(rs.getString("pol"));
                k.setEmail(rs.getString("email"));
                k.setTelefon(rs.getString("telefon"));
                k.setRanijeIsao(rs.getString("ranije_isao"));
                k.setOpisProblema(rs.getString("opis_problema"));
                k.setPrijavaId(rs.getInt("prijava_id"));
                lista.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
