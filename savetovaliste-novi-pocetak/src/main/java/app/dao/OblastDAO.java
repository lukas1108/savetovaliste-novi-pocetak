package app.dao;

import app.start.DatabaseConnection;
import app.model.Oblast;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OblastDAO {

    public Oblast getById(int id) {
        String sql = "SELECT * FROM oblast WHERE oblast_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Oblast o = new Oblast();
                o.setId(rs.getInt("oblast_id"));
                o.setNaziv(rs.getString("naziv"));
                o.setUsmerenjeId(rs.getInt("usmerenje_id"));
                return o;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Oblast> getAll() {
        List<Oblast> lista = new ArrayList<>();
        String sql = "SELECT * FROM oblast";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Oblast o = new Oblast();
                o.setId(rs.getInt("oblast_id"));
                o.setNaziv(rs.getString("naziv"));
                o.setUsmerenjeId(rs.getInt("usmerenje_id"));
                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
