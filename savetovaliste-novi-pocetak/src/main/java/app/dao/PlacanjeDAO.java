package app.dao;

import app.start.DatabaseConnection;
import app.model.Placanje;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlacanjeDAO {

    public Placanje getById(int id) {
        String sql = "SELECT * FROM placanje WHERE placanje_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Placanje p = new Placanje();
                p.setPlacanjeId(rs.getInt("placanje_id"));
                p.setSvrha(rs.getString("svrha"));
                p.setBrojRata(rs.getInt("broj_rata"));
                p.setJePrvaRata(rs.getBoolean("je_prva_rata"));
                p.setRokZaDruguRatu(rs.getDate("rok_za_drugu_ratu"));
                p.setNacinPlacanja(rs.getString("nacin_placanja"));
                p.setIznos(rs.getBigDecimal("iznos"));
                p.setDatumUplate(rs.getTimestamp("datum_uplate"));
                p.setProvizija(rs.getBigDecimal("provizija"));
                p.setValutaId(rs.getInt("valuta_id"));
                p.setSeansaId(rs.getInt("seansa_id"));
                return p;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Placanje> getAll() {
        List<Placanje> lista = new ArrayList<>();
        String sql = "SELECT * FROM placanje";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Placanje p = new Placanje();
                p.setPlacanjeId(rs.getInt("placanje_id"));
                p.setSvrha(rs.getString("svrha"));
                p.setBrojRata(rs.getInt("broj_rata"));
                p.setJePrvaRata(rs.getBoolean("je_prva_rata"));
                p.setRokZaDruguRatu(rs.getDate("rok_za_drugu_ratu"));
                p.setNacinPlacanja(rs.getString("nacin_placanja"));
                p.setIznos(rs.getBigDecimal("iznos"));
                p.setDatumUplate(rs.getTimestamp("datum_uplate"));
                p.setProvizija(rs.getBigDecimal("provizija"));
                p.setValutaId(rs.getInt("valuta_id"));
                p.setSeansaId(rs.getInt("seansa_id"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
