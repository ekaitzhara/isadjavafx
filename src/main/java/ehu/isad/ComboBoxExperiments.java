package ehu.isad;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
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

        primaryStage.setTitle("ComboBox Experiment Ekaitz");

        ComboBox comboBox = new ComboBox();
        listViewOfArgazki = new ListView<>();
        imageView = new ImageView();

        List<String> bildumak = List.of("Autoak", "Futbola", "Filmak");

        ObservableList<Bilduma> bildumaList = FXCollections.observableArrayList();

        bildumak.forEach((elem) -> {
            bildumaList.add(new Bilduma(elem));
        });

        comboBox.setItems(bildumaList);
        comboBox.setEditable(false);

        Map<String, List<Argazkia>> bildumaMap = new HashMap<>();

        bildumaMap.put("Autoak", List.of(
                new Argazkia("Lamborghini", "lambor.jpg"),
                new Argazkia("Ferrari", null),
                new Argazkia("Audi", "abt-audi-rs5-r.jpg"),
                new Argazkia("Mercedes", "mercedes-amg-a-cla-45-2019-0619-020_1440x655c.jpg"),
                new Argazkia("Volvo", "2020-volvo-xc60-exterior-056b.png")
        ));

        bildumaMap.put("Futbola", List.of(
                new Argazkia("Leo Messi", "messi.jpg"),
                new Argazkia("San Mames", "San-Mames.jpg"),
                new Argazkia("Aduriz", "aduriz.jpeg"),
                new Argazkia("Joao Felix", "joaoFelix.jpg"),
                new Argazkia("Ronaldo", null)
        ));

        bildumaMap.put("Filmak", List.of(
                new Argazkia("Star Wars", "694px-Star_Wars_Logo.svg.png"),
                new Argazkia("Avengers", "The_avengers_logo.png"),
                new Argazkia("Interstellar", null),
                new Argazkia("Spiderman", "spider.jpg")
        ));




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
            else if (observable.getValue().getFitx() == null) {
                try {
                    imageView.setImage(lortuIrudia("image-not-found.png"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                // SARTU ARGAZKIAK RESOURCES-EN
                String fitx = observable.getValue().getFitx();

                try {
                    imageView.setImage(lortuIrudia(fitx /* 48x48 */));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            imageView.setFitHeight(150);
            imageView.setFitWidth(200);
        });


        ComboBox hizkuntzaAldatu = new ComboBox();
        List<String> hizkuntzak = List.of("Euskara", "Castellano", "English");
        ObservableList<String> hizkuntzaList = FXCollections.observableArrayList();

        hizkuntzak.forEach((elem) -> {
            hizkuntzaList.add(elem);
        });

        hizkuntzaAldatu.setItems(hizkuntzaList);
        hizkuntzaAldatu.setEditable(false);

        hizkuntzaAldatu.setOnAction( e -> {
            String h = (String) hizkuntzaAldatu.getValue();
            if(h.equals("English"))
                Bilduma.aldatuHizkuntza("en","UK");
            else if(h.equals("Castellano"))
                Bilduma.aldatuHizkuntza("es","ES");
            else
                Bilduma.aldatuHizkuntza(null,null);
        });

        HBox hbuttons = new HBox();
        javafx.scene.control.Button botoia = new javafx.scene.control.Button("Exit");
        botoia.setOnAction( e -> {
            primaryStage.close();
        });

        hbuttons.getChildren().add(botoia);
        hbuttons.setAlignment(Pos.CENTER_RIGHT);


        VBox vbox = new VBox(comboBox, listViewOfArgazki, imageView, hizkuntzaAldatu, hbuttons);

        Scene scene = new Scene(vbox, 500, 340);
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
