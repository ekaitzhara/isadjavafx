package ehu.isad;

import java.util.Locale;
import java.util.ResourceBundle;

public class Bilduma {
    private String izena;
    private static Locale l;
    private static String hizkuntza;
    private static String herrialdea;

    public Bilduma(String izena) {
        this.izena = izena;
    }

    public String getIzena() {
        return izena;
    }

    public static void aldatuHizkuntza(String pHizkuntza, String pHerrialdea) {
        if(pHizkuntza != null) {
            hizkuntza = pHizkuntza;
            herrialdea = pHerrialdea;
            l = new Locale(hizkuntza, herrialdea);
        } else
            l = null;
    }

    @Override
    public String toString() {
        ResourceBundle res;
        if (l != null)
            res = ResourceBundle.getBundle("kateak", l);
        else
            res = ResourceBundle.getBundle("kateak");
        return res.getString(this.izena);
    }
}
