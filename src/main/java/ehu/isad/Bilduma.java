package ehu.isad;

public class Bilduma {
    private String izena;

    public Bilduma(String izena) {
        this.izena = izena;
    }

    public String getIzena() {
        return izena;
    }

    @Override
    public String toString() {
        return this.izena;
    }
}
