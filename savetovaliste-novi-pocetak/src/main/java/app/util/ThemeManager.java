package app.util;

import javafx.scene.Scene;
import javafx.scene.text.Font;

public class ThemeManager {

    private static String currentTheme = "light";
    private static boolean fontLoaded = false;

    public static void applyTheme(Scene scene) {
        if (!fontLoaded) {
            Font.loadFont(ThemeManager.class.getResource("/Roboto-VariableFont_wdth,wght.ttf").toExternalForm(), 14);
            fontLoaded = true;
        }

        scene.getStylesheets().clear();
        scene.getStylesheets().add(ThemeManager.class.getResource("/" + currentTheme + "-theme.css").toExternalForm());
    }

    public static void switchTheme() {
        currentTheme = currentTheme.equals("light") ? "dark" : "light";
    }

    public static String getCurrentTheme() {
        return currentTheme;
    }
}
