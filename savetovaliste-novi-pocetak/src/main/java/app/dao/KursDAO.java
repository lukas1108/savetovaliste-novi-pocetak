package app.dao;

import app.start.DatabaseConnection;
import app.model.Kurs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KursDAO {

    public Kurs getById(int id) {
        String sql = "SELECT * FROM kurs WHERE kurs_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Kurs k = new Kurs();
                k.setKursId(rs.getInt("kurs_id"));
                k.setDatum(rs.getDate("datum"));
                k.setKursZaRsd(rs.getDouble("kurs_za_rsd"));
                k.setValutaId(rs.getInt("valuta_id"));
                return k;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Kurs> getAll() {
        List<Kurs> lista = new ArrayList<>();
        String sql = "SELECT * FROM kurs";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Kurs k = new Kurs();
                k.setKursId(rs.getInt("kurs_id"));
                k.setDatum(rs.getDate("datum"));
                k.setKursZaRsd(rs.getDouble("kurs_za_rsd"));
                k.setValutaId(rs.getInt("valuta_id"));
                lista.add(k);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
