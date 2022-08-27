package net.justcoded.mc_core.utilities;

public class Parser {

    public static int parseInt(String toParse) {
        try {
            return Integer.parseInt(toParse);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return 0;
        }
    }

    public static boolean parseBoolean(String toParse) {
        try {
            return Boolean.parseBoolean(toParse);
        } catch (NumberFormatException ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
