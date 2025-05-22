package app.dao;

import app.start.DatabaseConnection;
import app.model.Fakultet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FakultetDAO {

    public Fakultet getById(int id) {
        String sql = "SELECT * FROM fakultet WHERE fakultet_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Fakultet f = new Fakultet();
                f.setFakultetId(rs.getInt("fakultet_id"));
                f.setNaziv(rs.getString("naziv"));
                f.setUniverzitetId(rs.getInt("univerzitet_id"));
                return f;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Fakultet> getAll() {
        List<Fakultet> lista = new ArrayList<>();
        String sql = "SELECT * FROM fakultet";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Fakultet f = new Fakultet();
                f.setFakultetId(rs.getInt("fakultet_id"));
                f.setNaziv(rs.getString("naziv"));
                f.setUniverzitetId(rs.getInt("univerzitet_id"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
