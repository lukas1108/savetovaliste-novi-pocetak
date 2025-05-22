package app.dao;

import app.start.DatabaseConnection;
import app.model.Valuta;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ValutaDAO {

    public Valuta getById(int id) {
        String sql = "SELECT * FROM valuta WHERE valuta_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Valuta v = new Valuta();
                v.setValutaId(rs.getInt("valuta_id"));
                v.setSkraceniNaziv(rs.getString("skraceni_naziv"));
                v.setPunNaziv(rs.getString("pun_naziv"));
                return v;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Valuta> getAll() {
        List<Valuta> lista = new ArrayList<>();
        String sql = "SELECT * FROM valuta";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Valuta v = new Valuta();
                v.setValutaId(rs.getInt("valuta_id"));
                v.setSkraceniNaziv(rs.getString("skraceni_naziv"));
                v.setPunNaziv(rs.getString("pun_naziv"));
                lista.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
