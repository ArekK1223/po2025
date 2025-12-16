package org.example.lab6;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.lab6.model.Samochod;
import org.example.lab6.model.Silnik;
import org.example.lab6.model.SkrzyniaBiegow;
import org.example.lab6.model.Sprzeglo;


public class HelloController {
    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Sprzeglo sprzeglo;
    private Map<String, Samochod> dostepneSamochody = new HashMap<>();
    private Samochod aktualnySamochod;

    @FXML private ComboBox<String> carComboBox;
    @FXML private ImageView carImageView;

    @FXML private TextField modelTextField;
    @FXML private TextField plateTextField;
    @FXML private TextField weightTextField;
    @FXML private TextField speedTextField;
    @FXML private TextField gearTextField;
    @FXML private TextField rpmTextField;
    @FXML private TextField clutchStateTextField;


    private static HelloController instance;
    private Samochod mojSamochod;

    public HelloController() {
        instance = this;
        mojSamochod = new Samochod();
        this.silnik = new Silnik(7000, 0);
        this.skrzyniaBiegow = new SkrzyniaBiegow(0, 6);
        this.sprzeglo = new Sprzeglo();
    }

    @FXML
    public void initialize() {
        System.out.println("HelloController initialized");

        Samochod audiA4 = new Samochod("Audi A4", "KR12345", 1550.0,
                new Silnik(7500, 0), new SkrzyniaBiegow(0, 7), new Sprzeglo());

        Samochod bmw3 = new Samochod("BMW 3 Series", "WR00001", 1480.0,
                new Silnik(8000, 0), new SkrzyniaBiegow(0, 6), new Sprzeglo());

        Samochod fiatPanda = new Samochod("Fiat Panda", "EL99999", 950.0,
                new Silnik(5500, 0), new SkrzyniaBiegow(0, 5), new Sprzeglo());

        String keyA4 = audiA4.getModel() + " (" + audiA4.getNrRejestracyjny() + ")";
        String keyBMW = bmw3.getModel() + " (" + bmw3.getNrRejestracyjny() + ")";
        String keyPanda = fiatPanda.getModel() + " (" + fiatPanda.getNrRejestracyjny() + ")";

        dostepneSamochody.put(keyA4, audiA4);
        dostepneSamochody.put(keyBMW, bmw3);
        dostepneSamochody.put(keyPanda, fiatPanda);

        carComboBox.getItems().addAll(dostepneSamochody.keySet());
        carComboBox.getSelectionModel().selectFirst();


        try {
            var resource = getClass().getResource("/images/car.png");
            if (resource != null) {
                Image carImage = new Image(resource.toExternalForm());
                carImageView.setImage(carImage);
                carImageView.setFitWidth(100);
                carImageView.setPreserveRatio(true);
            } else {
                System.out.println("Nie znaleziono pliku /images/car.png");
            }
        } catch (Exception e) {
            System.out.println("Błąd ładowania obrazka: " + e.getMessage());
        }

        onCarSelection();
    }


    @FXML
    private void onCarSelection() {
        String selectedCarKey = carComboBox.getSelectionModel().getSelectedItem();

        if (selectedCarKey != null && dostepneSamochody.containsKey(selectedCarKey)) {
            this.aktualnySamochod = dostepneSamochody.get(selectedCarKey);

            this.silnik = aktualnySamochod.getSilnik();
            this.skrzyniaBiegow = aktualnySamochod.getSkrzyniaBiegow();
            this.sprzeglo = aktualnySamochod.getSprzeglo();

            System.out.println("Wybrano: " + selectedCarKey + ". Max obroty: " + this.silnik.getMaxObroty());

            modelTextField.setText(aktualnySamochod.getModel());
            plateTextField.setText(aktualnySamochod.getNrRejestracyjny());
            weightTextField.setText(String.valueOf(aktualnySamochod.getWaga()));
        }
        refresh();
    }

    private void refresh() {
        if (mojSamochod != null) {
            System.out.println("Odświeżam widok...");
        }

        if (skrzyniaBiegow != null && gearTextField != null) {
            int aktualnyBieg = skrzyniaBiegow.getAktualnyBieg();
            gearTextField.setText(String.valueOf(aktualnyBieg));
        }

        if (silnik != null && rpmTextField != null) {
            int aktualneObroty = silnik.getObroty();
            rpmTextField.setText(String.valueOf(aktualneObroty));
        }
        if (sprzeglo != null && clutchStateTextField != null) {
            String stan = sprzeglo.getStanTekstowy();

            clutchStateTextField.setText(stan);

            System.out.println("DEBUG: Ustawiono stan sprzęgła: " + stan);
        }
    }

    @FXML
    private void onAddButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/DodajSamochod.fxml"));

            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            stage.setTitle("Dodaj nowy samochód");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCarToMapAndList(Samochod nowySamochod) {
        if (instance != null) {
            String key = nowySamochod.getModel() + " (" + nowySamochod.getNrRejestracyjny() + ")";

            instance.dostepneSamochody.put(key, nowySamochod);

            instance.carComboBox.getItems().add(key);

            instance.carComboBox.getSelectionModel().select(key);

            instance.onCarSelection();

            System.out.println("Dodano nowy samochód: " + key);
        }
    }

    @FXML
    private void onGearUpButton() {
        System.out.println("Zwiększ bieg");
        if (this.skrzyniaBiegow != null) {
            this.skrzyniaBiegow.zwiekszBieg();
        }
        refresh();
    }


    @FXML
    private void onStartButton() {
        System.out.println("Uruchomiono silnik (Start)");
        System.out.println("Zatrzymano silnik (Stop)");
        if (this.silnik != null) {
            this.silnik.uruchom();
        }
        refresh();
    }

    @FXML
    private void onStopButton() {

        System.out.println("Zatrzymano silnik (Stop)");
        if (this.silnik != null) {
            this.silnik.zatrzymaj();
        }
        refresh();
    }

    @FXML
    private void onGearDownButton() {
        System.out.println("Zmniejsz bieg");
        if (this.skrzyniaBiegow != null) {
            this.skrzyniaBiegow.zmniejszBieg();
        }
        refresh();
    }

    @FXML
    private void onGasButton() {
        System.out.println("Dodano gazu");

        if (this.silnik != null) {
            if (this.silnik.getObroty() > 0) {
                this.silnik.dodajGazu(200);
            } else {
                System.out.println("Silnik jest wyłączony. Najpierw go włącz.");
            }
        }

        refresh();
    }

    @FXML
    private void onBrakeButton() {
        System.out.println("Wciśnięto hamulec");

        if (this.silnik != null) {
            if (this.silnik.getObroty() > 0) {
                this.silnik.ujmijGazu(200);
            } else {
                System.out.println("Silnik jest wyłączony.");
            }
        }

        refresh();
    }
    @FXML
    private void onClutchPressButton() {
        System.out.println("Wciśnięto sprzęgło");
        if (this.sprzeglo != null) {
            this.sprzeglo.wcisnij();
        }
        refresh();
    }

    @FXML
    private void onClutchReleaseButton() {
        System.out.println("Puszczono sprzęgło");
        if (this.sprzeglo != null) {
            this.sprzeglo.zwolnij();
        }
        refresh();
    }
}