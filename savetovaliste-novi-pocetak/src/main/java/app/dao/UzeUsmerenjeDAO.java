package app.dao;

import app.start.DatabaseConnection;
import app.model.UzeUsmerenje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UzeUsmerenjeDAO {

    public UzeUsmerenje getById(int id) {
        String sql = "SELECT * FROM usmerenje WHERE usmerenje_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                UzeUsmerenje u = new UzeUsmerenje();
                u.setUsmerenjeId(rs.getInt("usmerenje_id"));
                u.setNaziv(rs.getString("naziv"));
                return u;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<UzeUsmerenje> getAll() {
        List<UzeUsmerenje> lista = new ArrayList<>();
        String sql = "SELECT * FROM usmerenje";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                UzeUsmerenje u = new UzeUsmerenje();
                u.setUsmerenjeId(rs.getInt("usmerenje_id"));
                u.setNaziv(rs.getString("naziv"));
                lista.add(u);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
