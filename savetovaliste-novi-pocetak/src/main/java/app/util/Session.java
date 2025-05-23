package app.util;

import app.model.Osoba;

public class Session {
    // klasa za trenutno ulogovanog usera
    private static Osoba currentUser;

    public static void setCurrentUser(Osoba user) {
        currentUser = user;
    }

    public static Osoba getCurrentUser() {
        return currentUser;
    }

    public static void clear() {
        currentUser = null;
    }
}