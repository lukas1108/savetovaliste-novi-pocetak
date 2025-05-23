package app.dao;

import app.start.DatabaseConnection;
import app.model.FakultetOblast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FakultetOblastDAO {

    public boolean exists(FakultetOblast f) {
        String sql = "SELECT 1 FROM fakultet_oblast WHERE fakultet_id = ? AND univerzitet_id = ? AND oblast_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, f.getFakultetId());
            stmt.setInt(2, f.getUniverzitetId());
            stmt.setInt(3, f.getOblastId());

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<FakultetOblast> getAll() {
        List<FakultetOblast> lista = new ArrayList<>();
        String sql = "SELECT * FROM fakultet_oblast";

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
        String sql = "{ CALL insert_fakultet_oblast(?, ?, ?) }";
        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, f.getFakultetId());
            cs.setInt(2, f.getUniverzitetId());
            cs.setInt(3, f.getOblastId());

            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}