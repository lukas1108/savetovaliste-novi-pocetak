package app.dao;

import app.start.DatabaseConnection;
import app.model.Seansa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeansaDAO {

    public Seansa getById(int id) {
        String sql = "SELECT * FROM seansa WHERE seansa_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Seansa seansa = new Seansa();
                seansa.setSeansaId(rs.getInt("seansa_id"));
                seansa.setDatum(rs.getDate("datum"));
                seansa.setVreme(rs.getTime("vreme"));
                seansa.setTrajanjeMin(rs.getInt("trajanje_min"));
                seansa.setBeleske(rs.getString("beleske"));
                seansa.setCenaId(rs.getInt("cena_id"));

                int objavaId = rs.getInt("objava_id");
                if (rs.wasNull()) {
                    seansa.setObjavaId(null);
                } else {
                    seansa.setObjavaId(objavaId);
                }

                int kandidatId = rs.getInt("kandidat_id");
                if (rs.wasNull()) {
                    seansa.setKandidatId(null);
                } else {
                    seansa.setKandidatId(kandidatId);
                }

                seansa.setOsobaId(rs.getInt("osoba_id"));
                return seansa;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Seansa> getAll() {
        List<Seansa> lista = new ArrayList<>();
        String sql = "SELECT * FROM seansa";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Seansa seansa = new Seansa();
                seansa.setSeansaId(rs.getInt("seansa_id"));
                seansa.setDatum(rs.getDate("datum"));
                seansa.setVreme(rs.getTime("vreme"));
                seansa.setTrajanjeMin(rs.getInt("trajanje_min"));
                seansa.setBeleske(rs.getString("beleske"));
                seansa.setCenaId(rs.getInt("cena_id"));

                int objavaId = rs.getInt("objava_id");
                if (rs.wasNull()) {
                    seansa.setObjavaId(null);
                } else {
                    seansa.setObjavaId(objavaId);
                }

                int kandidatId = rs.getInt("kandidat_id");
                if (rs.wasNull()) {
                    seansa.setKandidatId(null);
                } else {
                    seansa.setKandidatId(kandidatId);
                }

                seansa.setOsobaId(rs.getInt("osoba_id"));

                lista.add(seansa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
