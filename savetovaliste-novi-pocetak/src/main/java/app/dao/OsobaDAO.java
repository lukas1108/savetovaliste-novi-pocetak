package app.dao;

import app.start.DatabaseConnection;
import app.model.Oblast;
import app.model.Osoba;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OsobaDAO {

    public boolean updateSupervizijaId(int osobaId, int supervizijaId) {
        String sql = "UPDATE osoba SET supervizija_id = ? WHERE osoba_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, supervizijaId);
            ps.setInt(2, osobaId);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // za superviziju
    public Osoba getById(int id) {
        String sql = "SELECT * FROM osoba WHERE osoba_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
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
                o.setLozinka(rs.getString("lozinka"));

                int centarId = rs.getInt("centar_id");
                if (!rs.wasNull()) o.setCentarId(centarId);

                int supervizijaId = rs.getInt("supervizija_id");
                if (!rs.wasNull()) o.setSupervizijaId(supervizijaId);

                int oblastId = rs.getInt("oblast_id");
                if (!rs.wasNull()) o.setOblastId(oblastId);

                return o;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Integer addAndReturnId(Osoba o) {
        String sql = "{ CALL insert_osoba(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";

        try (Connection conn = DatabaseConnection.connect();
             CallableStatement cs = conn.prepareCall(sql)) {

            cs.setString(1, o.getIme());
            cs.setString(2, o.getPrezime());
            cs.setString(3, o.getJmbg());
            cs.setDate(4, o.getDatumRodjenja());
            cs.setString(5, o.getPol());
            cs.setString(6, o.getEmail());
            cs.setString(7, o.getTelefon());
            cs.setString(8, o.getUlica());
            cs.setInt(9, o.getBroj());
            cs.setString(10, o.getOpstina());
            cs.setString(11, o.getStepenStudija());
            cs.setDate(12, o.getDatumSertifikacije());
            if (o.getCentarId() != null) cs.setInt(13, o.getCentarId()); else cs.setNull(13, Types.INTEGER);
            cs.setInt(14, o.getFakultetId());
            cs.setInt(15, o.getUniverzitetId());
            if (o.getSupervizijaId() != null) cs.setInt(16, o.getSupervizijaId()); else cs.setNull(16, Types.INTEGER);
            cs.setString(17, o.getTipOsobe());
            if (o.getOblastId() != null) cs.setInt(18, o.getOblastId()); else cs.setNull(18, Types.INTEGER);
            cs.setString(19, o.getLozinka());

            cs.registerOutParameter(20, Types.INTEGER);

            cs.execute();

            return cs.getInt(20);

        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
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
                o.setLozinka(rs.getString("lozinka"));

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

