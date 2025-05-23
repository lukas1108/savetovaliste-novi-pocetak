package app.dao;

import app.model.Klijent;
import app.start.DatabaseConnection;
import app.model.Seansa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeansaDAO {

    public void updateObjavaId(int seansaId, int objavaId) {
        String sql = "{ CALL update_seansa_objava(?, ?) }";

        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, seansaId);
            cs.setInt(2, objavaId);
            cs.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateBeleske(int seansaId, String noveBeleske) {
        String sql = "{ CALL update_seansa_beleske(?, ?) }";

        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setInt(1, seansaId);           // prvo ide seansa_id
            cs.setString(2, noveBeleske);     // zatim beleške
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Seansa> getPastSessionsForTherapist(int osobaId) {
        List<Seansa> lista = new ArrayList<>();
        String sql = "SELECT * FROM seansa WHERE osoba_id = ? AND datum < CURDATE()";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, osobaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapSeansa(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public List<Seansa> getUpcomingSessionsForTherapist(int osobaId) {
        List<Seansa> lista = new ArrayList<>();
        String sql = "SELECT * FROM seansa WHERE osoba_id = ? AND datum >= CURDATE()";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, osobaId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(mapSeansa(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Seansa mapSeansa(ResultSet rs) throws SQLException {
        Seansa seansa = new Seansa();
        seansa.setSeansaId(rs.getInt("seansa_id"));
        seansa.setDatum(rs.getDate("datum"));
        seansa.setVreme(rs.getTime("vreme"));
        seansa.setTrajanjeMin(rs.getInt("trajanje_min"));
        seansa.setBeleske(rs.getString("beleske"));
        seansa.setCenaId(rs.getInt("cena_id"));

        int objavaId = rs.getInt("objava_id");
        seansa.setObjavaId(rs.wasNull() ? null : objavaId);

        seansa.setOsobaId(rs.getInt("osoba_id"));

        int klijentId = rs.getInt("klijent_id");
        if (!rs.wasNull()) {
            KlijentDAO klijentDAO = new KlijentDAO();
            Klijent klijent = klijentDAO.getById(klijentId);
            seansa.setKlijent(klijent);
        }

        return seansa;
    }

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

                seansa.setOsobaId(rs.getInt("osoba_id"));

                lista.add(seansa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
