package app.dao;

import app.start.DatabaseConnection;
import app.model.CenaSeanse;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CenaSeanseDAO {
    // DAO = DATA ACCESS OBJECT

    public void dodajCenuSeanse(java.sql.Date datumPromene, double novaCena) {
        String sql = "{ CALL insert_cena_seanse(?, ?) }";

        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setDate(1, datumPromene);
            cs.setDouble(2, novaCena);
            cs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public CenaSeanse getById(int id) {
        String sql = "SELECT * FROM cena_seanse WHERE cena_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                CenaSeanse c = new CenaSeanse();
                c.setCenaId(rs.getInt("cena_id"));
                c.setDatumPromene(rs.getDate("datum_promene"));
                c.setNovaCena(rs.getDouble("nova_cena"));
                return c;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<CenaSeanse> getAll() {
        List<CenaSeanse> lista = new ArrayList<>();
        String sql = "SELECT * FROM CenaSeanse";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                CenaSeanse c = new CenaSeanse();
                c.setCenaId(rs.getInt("cena_id"));
                c.setDatumPromene(rs.getDate("datum_promene"));
                c.setNovaCena(rs.getDouble("nova_cena"));
                lista.add(c);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
