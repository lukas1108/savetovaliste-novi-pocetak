package app.dao;

import app.start.DatabaseConnection;
import app.model.PsiholoskiTest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PsiholoskiTestDAO {

    public PsiholoskiTest getById(int id) {
        String sql = "SELECT * FROM psiholoski_test WHERE test_id = ?";
        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                PsiholoskiTest test = new PsiholoskiTest();
                test.setTestId(rs.getInt("test_id"));
                test.setNaziv(rs.getString("naziv"));
                test.setOblast(rs.getString("oblast"));
                test.setCena(rs.getBigDecimal("cena"));
                test.setRezultat(rs.getString("rezultat"));
                test.setSeansaId(rs.getInt("seansa_id"));
                return test;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<PsiholoskiTest> getAll() {
        List<PsiholoskiTest> lista = new ArrayList<>();
        String sql = "SELECT * FROM psiholoski_test";

        try (Connection conn = DatabaseConnection.connect();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                PsiholoskiTest test = new PsiholoskiTest();
                test.setTestId(rs.getInt("test_id"));
                test.setNaziv(rs.getString("naziv"));
                test.setOblast(rs.getString("oblast"));
                test.setCena(rs.getBigDecimal("cena"));
                test.setRezultat(rs.getString("rezultat"));
                test.setSeansaId(rs.getInt("seansa_id"));
                lista.add(test);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
