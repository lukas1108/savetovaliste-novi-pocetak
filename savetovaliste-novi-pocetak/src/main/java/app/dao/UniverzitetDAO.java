package app.dao;

import app.start.DatabaseConnection;
import app.model.Univerzitet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UniverzitetDAO {

    public Univerzitet getById(int id) {
        String sql = "SELECT * FROM univerzitet WHERE univerzitet_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Univerzitet u = new Univerzitet();
                u.setUniverzitetId(rs.getInt("univerzitet_id"));
                u.setNaziv(rs.getString("naziv"));
                u.setUsmerenjeId(rs.getInt("usmerenje_id"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Univerzitet> getAll() {
        List<Univerzitet> lista = new ArrayList<>();
        String sql = "SELECT * FROM univerzitet";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Univerzitet u = new Univerzitet();
                u.setUniverzitetId(rs.getInt("univerzitet_id"));
                u.setNaziv(rs.getString("naziv"));
                u.setUsmerenjeId(rs.getInt("usmerenje_id"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
