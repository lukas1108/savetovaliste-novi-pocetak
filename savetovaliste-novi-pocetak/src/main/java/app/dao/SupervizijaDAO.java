package app.dao;

import app.start.DatabaseConnection;
import app.model.Supervizija;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupervizijaDAO {

    public Supervizija getById(int id) {
        String sql = "SELECT * FROM supervizija WHERE supervizija_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Supervizija s = new Supervizija();
                s.setSupervizijaId(rs.getInt("supervizija_id"));
                s.setPocetak(rs.getDate("pocetak"));
                s.setKraj(rs.getDate("kraj"));
                s.setPsihoterapeutId(rs.getInt("psihoterapeut_id"));
                s.setKandidatId(rs.getInt("kandidat_id"));
                return s;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Supervizija> getAll() {
        List<Supervizija> lista = new ArrayList<>();
        String sql = "SELECT * FROM supervizija";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Supervizija s = new Supervizija();
                s.setSupervizijaId(rs.getInt("supervizija_id"));
                s.setPocetak(rs.getDate("pocetak"));
                s.setKraj(rs.getDate("kraj"));
                s.setPsihoterapeutId(rs.getInt("psihoterapeut_id"));
                s.setKandidatId(rs.getInt("kandidat_id"));

                lista.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
