package app.dao;

import app.start.DatabaseConnection;
import app.model.Prijava;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrijavaDAO {

    public Prijava getById(int id) {
        String sql = "SELECT * FROM prijava WHERE prijava_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Prijava p = new Prijava();
                p.setPrijavaId(rs.getInt("prijava_id"));
                p.setDatum(rs.getDate("datum"));
                p.setOsobaId(rs.getInt("osoba_id"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Prijava> getAll() {
        List<Prijava> lista = new ArrayList<>();
        String sql = "SELECT * FROM prijava";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Prijava p = new Prijava();
                p.setPrijavaId(rs.getInt("prijava_id"));
                p.setDatum(rs.getDate("datum"));
                p.setOsobaId(rs.getInt("osoba_id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
