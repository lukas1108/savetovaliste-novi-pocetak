package app.dao;

import app.start.DatabaseConnection;
import app.model.Objavljivanje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ObjavljivanjeDAO {

    public int insertAndReturnId(Objavljivanje o) {
        String sql = "{ CALL insert_objavljivanje(?, ?, ?, ?) }";

        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setDate(1, o.getDatum());
            cs.setString(2, o.getPrimalac());
            cs.setString(3, o.getRazlogObjave());
            cs.registerOutParameter(4, Types.INTEGER);

            cs.execute();

            return cs.getInt(4); // povratni ID

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }
    public Objavljivanje getById(int id) {
        String sql = "SELECT * FROM objavljivanje WHERE objava_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Objavljivanje o = new Objavljivanje();
                o.setObjavaId(rs.getInt("objava_id"));
                o.setDatum(rs.getDate("datum"));
                o.setPrimalac(rs.getString("primalac"));
                o.setRazlogObjave(rs.getString("razlog_objave"));
                return o;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Objavljivanje> getAll() {
        List<Objavljivanje> lista = new ArrayList<>();
        String sql = "SELECT * FROM objavljivanje";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Objavljivanje o = new Objavljivanje();
                o.setObjavaId(rs.getInt("objava_id"));
                o.setDatum(rs.getDate("datum"));
                o.setPrimalac(rs.getString("primalac"));
                o.setRazlogObjave(rs.getString("razlog_objave"));
                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
