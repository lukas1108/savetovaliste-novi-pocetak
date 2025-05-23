package app.test;

import app.start.DatabaseConnection;

import java.sql.*;

public class Testing {
    // SQL UPITI ZA TESTIRANJE
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.connect()) {

            System.out.println("\n🔹 Broj kandidata po centru za obuku:");
            testUpit(conn, """
                SELECT centar_za_obuku.naziv AS centar, COUNT(osoba.osoba_id) AS broj_kandidata
                FROM centar_za_obuku
                LEFT JOIN osoba ON centar_za_obuku.centar_id = osoba.centar_id AND osoba.tip_osobe = 'kandidat'
                GROUP BY centar_za_obuku.centar_id
            """);

            System.out.println("\n🔹 Broj terapeuta po oblasti:");
            testUpit(conn, """
                SELECT oblast.naziv AS oblast, COUNT(osoba.osoba_id) AS broj_terapeuta
                FROM oblast
                JOIN osoba ON osoba.oblast_id = oblast.oblast_id
                WHERE osoba.tip_osobe = 'psihoterapeut'
                GROUP BY oblast.oblast_id
            """);

            System.out.println("\n🔹 Seanse koje nisu objavljene:");
                        testUpit(conn, """
                SELECT seansa.seansa_id, seansa.datum, seansa.vreme
                FROM seansa
                WHERE seansa.objava_id IS NULL
            """);

            System.out.println("🔹 Broj klijenata po terapeutima:");
                        testUpit(conn, """
                SELECT osoba.ime, osoba.prezime, COUNT(DISTINCT seansa.klijent_id) AS broj_klijenata
                FROM seansa
                JOIN osoba ON seansa.osoba_id = osoba.osoba_id
                GROUP BY seansa.osoba_id
            """);

            System.out.println("\n🔹 Ukupno plaćeno po valuti:");
            testUpit(conn, """
                SELECT valuta.skraceni_naziv, SUM(placanje.iznos) AS ukupno
                FROM placanje
                JOIN valuta ON placanje.valuta_id = valuta.valuta_id
                GROUP BY valuta.valuta_id
            """);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testUpit(Connection conn, String sql) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            ResultSetMetaData meta = rs.getMetaData();
            int kolona = meta.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= kolona; i++) {
                    System.out.print(meta.getColumnLabel(i) + ": " + rs.getString(i) + "  ");
                }
                System.out.println();
            }
        }
    }
}