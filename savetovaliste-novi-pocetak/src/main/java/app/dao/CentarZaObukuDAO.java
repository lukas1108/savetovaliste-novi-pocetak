package app.dao;

import app.model.CentarZaObuku;
import app.start.DatabaseConnection;

import java.sql.*;

public class CentarZaObukuDAO {
    private Connection connection;

    public CentarZaObukuDAO(Connection connection) {
        this.connection = connection;
    }

    public static String getNazivById(int id) {
        String sql = "SELECT naziv FROM centar_za_obuku WHERE centar_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) return rs.getString("naziv");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Nepoznato";
    }


    public static CentarZaObuku nadjiIliKreirajCentar(String naziv, String email, String telefon, String ulica, int broj, String opstina) throws SQLException {
        Connection conn = DatabaseConnection.connect();
        CentarZaObukuDAO dao = new CentarZaObukuDAO(conn);

        CentarZaObuku centar = dao.nadjiCentarPoNazivu(naziv);
        if (centar != null) {
            return centar;
        }

        // pravi novi centar
        CentarZaObuku novi = new CentarZaObuku(0, naziv, email, telefon, ulica, broj, opstina);
        int noviId = dao.kreirajCentar(novi);
        novi.setCentarId(noviId);
        return novi;
    }


    public CentarZaObuku nadjiCentarPoNazivu(String naziv) throws SQLException {
        String sql = "SELECT * FROM centar_za_obuku WHERE LOWER(naziv) = LOWER(?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, naziv);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new CentarZaObuku(
                        rs.getInt("centar_id"),
                        rs.getString("naziv"),
                        rs.getString("email"),
                        rs.getString("telefon"),
                        rs.getString("ulica"),
                        rs.getInt("broj"),
                        rs.getString("opstina")
                );
            }
            return null;
        }
    }

    public int kreirajCentar(CentarZaObuku centar) throws SQLException {
        String sql = "{ CALL insert_centar_za_obuku(?, ?, ?, ?, ?, ?) }";
        try (CallableStatement cs = connection.prepareCall(sql)) {
            cs.setString(1, centar.getNaziv());
            cs.setString(2, centar.getEmail());
            cs.setString(3, centar.getTelefon());
            cs.setString(4, centar.getUlica());
            cs.setInt(5, centar.getBroj());
            cs.setString(6, centar.getOpstina());

            cs.executeUpdate();
        }

        String getIdSql = "SELECT centar_id FROM centar_za_obuku WHERE naziv = ?";
        try (PreparedStatement ps = connection.prepareStatement(getIdSql)) {
            ps.setString(1, centar.getNaziv());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("centar_id");
            } else {
                throw new SQLException("Neuspesno dobijanje ID-a centra.");
            }
        }
    }
}

