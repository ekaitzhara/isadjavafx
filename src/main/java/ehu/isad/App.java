/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package ehu.isad;

import java.lang.reflect.Array;

public class App {

    private String bildumaIzena;
    private String idArgazkiak[];
    private String izenArgazkiak[];

    public App(String bIzena, String pIdArgazkiak[], String pIzenArgazkiak[]) {
        this.bildumaIzena = bIzena;
        this.idArgazkiak = pIdArgazkiak;
        this.izenArgazkiak = pIzenArgazkiak;
    }

    public String getBildumaIzenazen() {
        return this.bildumaIzena;
    }

    public String[] getIzenArgazkiak() {
        return this.izenArgazkiak;
    }

    public String[] getIdArgazkiak() {
        return this.idArgazkiak;
    }

    public String getGreeting() {
        return "Hello world.";
    }

    @Override
    public String toString() {
        return this.bildumaIzena;
    }

    public static void main(String[] args) {
        String arId[] = {"1","2"};
        String arIzen[] = {"2","3"};
        App a = new App("s", arId, arIzen);
        System.out.println(a.toString());
    }
}
