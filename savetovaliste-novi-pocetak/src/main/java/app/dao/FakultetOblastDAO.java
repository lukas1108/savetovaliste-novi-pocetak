package app.dao;

import app.start.DatabaseConnection;
import app.model.FakultetOblast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FakultetOblastDAO {

    public List<FakultetOblast> getAll() {
        List<FakultetOblast> lista = new ArrayList<>();
        String sql = "SELECT * FROM fakultet_univerzitet_oblast";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                FakultetOblast f = new FakultetOblast();
                f.setFakultetId(rs.getInt("fakultet_id"));
                f.setUniverzitetId(rs.getInt("univerzitet_id"));
                f.setOblastId(rs.getInt("oblast_id"));
                lista.add(f);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public void insert(FakultetOblast f) {
        String sql = "INSERT INTO fakultet_univerzitet_oblast (fakultet_id, univerzitet_id, oblast_id) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, f.getFakultetId());
            stmt.setInt(2, f.getUniverzitetId());
            stmt.setInt(3, f.getOblastId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
