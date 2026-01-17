package org.example.lab6;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.example.lab6.model.Samochod;
import org.example.lab6.model.Silnik;
import org.example.lab6.model.SkrzyniaBiegow;
import org.example.lab6.model.Sprzeglo;
import org.example.lab6.model.Listener;
import org.example.lab6.model.Pozycja;


public class HelloController implements Listener{
    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Sprzeglo sprzeglo;
    private Map<String, Samochod> dostepneSamochody = new HashMap<>();
    private Samochod aktualnySamochod;

    @FXML private ComboBox<String> carComboBox;
    @FXML private ImageView carImageView;

    @FXML private Pane mapa;

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
                new Silnik(7500, 0), new SkrzyniaBiegow(0, 7), new Sprzeglo(), "/images/audi.png");

        Samochod bmw3 = new Samochod("BMW 3 Series", "WR00001", 1480.0,
                new Silnik(8000, 0), new SkrzyniaBiegow(0, 6), new Sprzeglo(), "/images/bmw.png");
        Samochod fiatPanda = new Samochod("Fiat Panda", "EL99999", 950.0,
                new Silnik(5500, 0), new SkrzyniaBiegow(0, 5), new Sprzeglo(), "/images/fiat.png");

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
        if (mapa != null) {
            mapa.setOnMouseClicked(event -> { // [cite: 49]
                if (aktualnySamochod != null) {
                    double x = event.getX();
                    double y = event.getY();
                    System.out.println("Kliknięto mapę: " + x + ", " + y);

                    // Tworzymy cel (odejmujemy połowę wielkości auta, żeby środek auta jechał do kursora)
                    Pozycja cel = new Pozycja(x - 25, y - 25);
                    aktualnySamochod.jedzDo(cel);
                }
            });
        } else {
            System.err.println("UWAGA: Pole 'mapa' jest null. Sprawdź fx:id w FXML!");
        }

        onCarSelection();
    }



    @FXML
    private void onCarSelection() {
        // 1. Odłączamy nasłuchiwanie od STAREGO samochodu
        if (aktualnySamochod != null) {
            aktualnySamochod.removeListener(this);
        }

        String selectedCarKey = carComboBox.getSelectionModel().getSelectedItem();

        if (selectedCarKey != null && dostepneSamochody.containsKey(selectedCarKey)) {
            // Przypisanie nowego samochodu
            this.aktualnySamochod = dostepneSamochody.get(selectedCarKey);

            // 2. Podłączamy nasłuchiwanie do NOWEGO samochodu
            this.aktualnySamochod.addListener(this);

            // Przypisanie komponentów
            this.silnik = aktualnySamochod.getSilnik();
            this.skrzyniaBiegow = aktualnySamochod.getSkrzyniaBiegow();
            this.sprzeglo = aktualnySamochod.getSprzeglo();

            System.out.println("Wybrano: " + selectedCarKey);

            // Aktualizacja pól tekstowych
            modelTextField.setText(aktualnySamochod.getModel());
            plateTextField.setText(aktualnySamochod.getNrRejestracyjny());
            weightTextField.setText(String.valueOf(aktualnySamochod.getWaga()));

            // --- NOWA CZĘŚĆ: ŁADOWANIE OBRAZKA ---
            // Czyścimy stary obrazek, żeby mieć pewność, że zmiana nastąpiła
            carImageView.setImage(null);

            String sciezka = aktualnySamochod.getSciezkaDoObrazka();
            if (sciezka != null && !sciezka.isEmpty()) {
                try {
                    // Ładujemy plik z resources
                    var resource = getClass().getResource(sciezka);
                    if (resource != null) {
                        Image image = new Image(resource.toExternalForm());
                        carImageView.setImage(image);
                    } else {
                        System.err.println("BŁĄD: Nie znaleziono pliku: " + sciezka);
                    }
                } catch (Exception e) {
                    System.err.println("Błąd ładowania obrazka: " + e.getMessage());
                }
            }
            // -------------------------------------
        }

        // Odświeżenie widoku na start
        refresh();
    }
    private void refresh() {
        // 3. BRAKUJĄCA AKTUALIZACJA POZYCJI OBRAZKA
        if (aktualnySamochod != null && carImageView != null) {
            // Pobieramy aktualną pozycję z modelu i ustawiamy na widoku
            carImageView.setTranslateX(aktualnySamochod.getPozycja().getX());
            carImageView.setTranslateY(aktualnySamochod.getPozycja().getY());
        }

        if (mojSamochod != null) {
            // System.out.println("Odświeżam widok..."); // Możesz zakomentować, żeby nie śmiecić w konsoli
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
            clutchStateTextField.setText(sprzeglo.getStanTekstowy());
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

    @Override
    public void update() {
        // [cite: 58, 162] Ważne: Odświeżanie GUI musi być w Platform.runLater,
        // ponieważ wątek Samochodu jest inny niż wątek JavaFX.
        Platform.runLater(() -> {
            refresh(); // Wywołanie twojej metody odświeżającej widok
        });
    }
}