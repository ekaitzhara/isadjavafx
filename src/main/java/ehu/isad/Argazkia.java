package ehu.isad;

public class Argazkia {

    private String izena;
    private String path;

    public Argazkia(String izena, String path) {
        this.izena = izena;
        this.path = path;
    }

    public String getFitx() {
        return this.path;
    }

    @Override
    public String toString() {
        return this.izena;
    }
}
