package app.dao;

import app.start.DatabaseConnection;
import app.model.Oblast;
import app.model.Osoba;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OsobaDAO {

    public static boolean add(Osoba o) {
        String sql = "INSERT INTO osoba (ime, prezime, jmbg, datum_rodjenja, pol, email, telefon, ulica, broj, opstina, " +
                "stepen_studija, datum_sertifikacije, centar_id, fakultet_id, univerzitet_id, supervizija_id, tip_osobe, oblast_id, lozinka) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, o.getIme());
            ps.setString(2, o.getPrezime());
            ps.setString(3, o.getJmbg());
            ps.setDate(4, o.getDatumRodjenja());
            ps.setString(5, o.getPol());
            ps.setString(6, o.getEmail());
            ps.setString(7, o.getTelefon());
            ps.setString(8, o.getUlica());
            ps.setInt(9, o.getBroj());
            ps.setString(10, o.getOpstina());
            ps.setString(11, o.getStepenStudija());
            ps.setDate(12, o.getDatumSertifikacije());  // moze i null
            if (o.getCentarId() != null) ps.setInt(13, o.getCentarId()); else ps.setNull(13, java.sql.Types.INTEGER);
            ps.setInt(14, o.getFakultetId());
            ps.setInt(15, o.getUniverzitetId());
            if (o.getSupervizijaId() != null) ps.setInt(16, o.getSupervizijaId()); else ps.setNull(16, java.sql.Types.INTEGER);
            ps.setString(17, o.getTipOsobe());
            if (o.getOblastId() != null) ps.setInt(18, o.getOblastId()); else ps.setNull(18, java.sql.Types.INTEGER);
            ps.setString(19, /* ovde ide hashovana lozinka ili plain ako je za sada */ o.getLozinka());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    public List<Osoba> getAll() {
        List<Osoba> lista = new ArrayList<>();
        String sql = "SELECT * FROM osoba";
        OblastDAO oblastDAO = new OblastDAO(); // koristi se za dohvatanje naziva oblasti

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Osoba o = new Osoba();
                o.setId(rs.getInt("osoba_id"));
                o.setIme(rs.getString("ime"));
                o.setPrezime(rs.getString("prezime"));
                o.setJmbg(rs.getString("jmbg"));
                o.setDatumRodjenja(rs.getDate("datum_rodjenja"));
                o.setPol(rs.getString("pol"));
                o.setEmail(rs.getString("email"));
                o.setTelefon(rs.getString("telefon"));
                o.setUlica(rs.getString("ulica"));
                o.setBroj(rs.getInt("broj"));
                o.setOpstina(rs.getString("opstina"));
                o.setStepenStudija(rs.getString("stepen_studija"));
                o.setDatumSertifikacije(rs.getDate("datum_sertifikacije"));
                o.setFakultetId(rs.getInt("fakultet_id"));
                o.setUniverzitetId(rs.getInt("univerzitet_id"));
                o.setTipOsobe(rs.getString("tip_osobe"));

                Integer centarId = null;
                int tmpCentarId = rs.getInt("centar_id");
                if (!rs.wasNull()) {
                    centarId = tmpCentarId;
                }
                o.setCentarId(centarId);

                Integer supervizijaId = null;
                int tmpSupervizijaId = rs.getInt("supervizija_id");
                if (!rs.wasNull()) {
                    supervizijaId = tmpSupervizijaId;
                }
                o.setSupervizijaId(supervizijaId);

                Integer oblastId = null;
                int tmpOblastId = rs.getInt("oblast_id");
                if (!rs.wasNull()) {
                    oblastId = tmpOblastId;
                }
                o.setOblastId(oblastId);

                if (oblastId != null) {
                    Oblast oblast = oblastDAO.getById(oblastId);
                    if (oblast != null) {
                        o.setOblastNaziv(oblast.getNaziv());
                    }
                }

                lista.add(o);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}

