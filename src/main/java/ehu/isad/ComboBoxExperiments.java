package ehu.isad;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ehu.isad.App;

import javax.imageio.ImageIO;


public class ComboBoxExperiments extends Application  {

    private ListView<Argazkia> listViewOfArgazki;
    private ImageView imageView;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("ComboBox Experiment 1");

        ComboBox comboBox = new ComboBox();
        listViewOfArgazki = new ListView<>();
        imageView = new ImageView();

        List<String> bildumak = List.of("Kotxeak", "Futbola", "Filmak");

        ObservableList<Bilduma> bildumaList = FXCollections.observableArrayList();

        bildumak.forEach((elem) -> {
            bildumaList.add(new Bilduma(elem));
        });

        comboBox.setItems(bildumaList);
        comboBox.setEditable(false);

        Map<String, List<Argazkia>> bildumaMap = new HashMap<>();

        bildumaMap.put("Kotxeak", List.of(
                new Argazkia("Lamborghini", ""),
                new Argazkia("Ferrari", ""),
                new Argazkia("Audi", ""),
                new Argazkia("Mercedes", ""),
                new Argazkia("Volvo", "")
        ));

        bildumaMap.put("Futbola", List.of(
                new Argazkia("Leo Messi", ""),
                new Argazkia("San Mames", "San-Mames.jpg"),
                new Argazkia("Aduriz", "aduriz.jpeg"),
                new Argazkia("Joao Felix", "")
        ));

        bildumaMap.put("Filmak", List.of(
                new Argazkia("Star Wars", ""),
                new Argazkia("Avengers", ""),
                new Argazkia("Spiderman", "")
        ));


        // poner una imagen predeterminada para que aparezca al principio de la applicacion



        comboBox.setOnAction(e -> {
            Bilduma b = (Bilduma) comboBox.getValue();
            String bildumIzen = b.getIzena();

            ObservableList<Argazkia> argazkiList =
                    FXCollections.observableArrayList();
            argazkiList.addAll(bildumaMap.get(bildumIzen));

            listViewOfArgazki.getItems().clear();
            listViewOfArgazki.setItems(argazkiList);

        });


        listViewOfArgazki.getSelectionModel().selectedItemProperty().addListener(  (observable, oldValue, newValue) -> {

            // Ez dagoenean sortu argazki bat ez dagoela esateko --> errorea ez emateko
            if (observable.getValue() == null) return;

            // SARTU ARGAZKIAK RESOURCES-EN
            String fitx = observable.getValue().getFitx();

            try {
                imageView.setImage(lortuIrudia(fitx /* 48x48 */));
                imageView.setFitHeight(100);
                imageView.setFitWidth(100);
            } catch (IOException e) {
                e.printStackTrace();
            }

        });


        /*
        FileInputStream input = new FileInputStream("/home/ekaitzhara/Imágenes/thumb-1920-707960.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        */


        //comboBox.getSelectionModel().selectFirst();

        /*
        comboBox.setOnAction(e -> {
            App bildum = (App) comboBox.getValue();
            String izenak[] = bildum.getIzenArgazkiak();
            argazkiak.getItems().clear();
            for (int i = 0; i < izenak.length; i++)
                argazkiak.getItems().add(izenak[i]);

            String idak[] = bildum.getIdArgazkiak();
            FileInputStream input = null;
            Image image;

            if (idak[0]!=null) {
                try {
                    input = new FileInputStream("/home/ekaitzhara/Imágenes/" + idak[0]);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                image = new Image(input);
                imageView = new ImageView(image);
                imageView.setImage(image);
            }
        });

         */



        /*
        argazkiak.setOnAction(e -> {
            String izenak[] = bildum.getIzenArgazkiak();
            String idak[] = bildum.getIdArgazkiak();
            String nahiDuzuna = argazkiak.getValue();
            int pos=0;
            boolean aurkituta = false;
            while (!aurkituta) {
                if (izenak[pos].equals(nahiDuzuna))
                    aurkituta=true;
            }
            FileInputStream input = null;
            Image image;
            if (idak[pos]!=null) {
                try {
                    input = new FileInputStream("/home/ekaitzhara/Imágenes/" + idak[i]);
                } catch (FileNotFoundException ex) {
                    ex.printStackTrace();
                }
                image = new Image(input);
                imageView = new ImageView(image);
                imageView.setImage(image);
            }

        });
         */


        //HBox hbox = new HBox(comboBox);
        VBox vbox = new VBox(comboBox, listViewOfArgazki, imageView);

        Scene scene = new Scene(vbox, 400, 240);
        primaryStage.setScene(scene);



        primaryStage.show();

    }

    private Image lortuIrudia(String location) throws IOException {

        InputStream is = getClass().getResourceAsStream("/" + location);
        BufferedImage reader = ImageIO.read(is);
        return SwingFXUtils.toFXImage(reader, null);

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
