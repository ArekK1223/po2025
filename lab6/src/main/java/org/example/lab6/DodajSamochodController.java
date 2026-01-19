package org.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.lab6.model.Samochod;
import org.example.lab6.model.Silnik;
import org.example.lab6.model.SkrzyniaBiegow;
import org.example.lab6.model.Sprzeglo;

public class DodajSamochodController {

    // --- Sekcja Samochód ---
    @FXML private TextField modelInput;
    @FXML private TextField plateInput;
    @FXML private TextField weightInput;

    // --- Sekcja Silnik ---
    @FXML private TextField engineNameInput;
    @FXML private TextField enginePriceInput;
    @FXML private TextField engineWeightInput;
    @FXML private TextField maxRpmInput;

    // --- Sekcja Skrzynia ---
    @FXML private TextField gearboxNameInput;
    @FXML private TextField gearboxPriceInput;
    @FXML private TextField gearboxWeightInput;
    @FXML private TextField maxGearsInput;

    // --- Sekcja Sprzęgło ---
    @FXML private TextField clutchNameInput;
    @FXML private TextField clutchPriceInput;
    @FXML private TextField clutchWeightInput;

    @FXML
    private void onCancelButton() {
        Stage stage = (Stage) modelInput.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onDodajButton() {
        try {
            // 1. Walidacja podstawowa
            String model = modelInput.getText().trim();
            String registration = plateInput.getText().trim();

            if (model.isEmpty() || registration.isEmpty()) {
                System.err.println("Błąd: Model i nr rejestracyjny są wymagane!");
                return;
            }

            // 2. Pobieranie danych Samochodu
            double carWeight = Double.parseDouble(weightInput.getText());

            // 3. Tworzenie SILNIKA (Nazwa, Cena, Waga, MaxRPM, StartoweRPM)
            String engName = engineNameInput.getText();
            double engPrice = Double.parseDouble(enginePriceInput.getText());
            double engWeight = Double.parseDouble(engineWeightInput.getText());
            int maxRpm = Integer.parseInt(maxRpmInput.getText());

            // Konstruktor zgodny z HelloController (nazwa, cena, waga, maxRpm, aktualneRpm)
            Silnik nowySilnik = new Silnik(engName, engPrice, engWeight, maxRpm, 0);

            // 4. Tworzenie SKRZYNI (Nazwa, Cena, Waga, StartowyBieg, MaxBiegow)
            String gearName = gearboxNameInput.getText();
            double gearPrice = Double.parseDouble(gearboxPriceInput.getText());
            double gearWeight = Double.parseDouble(gearboxWeightInput.getText());
            int maxGears = Integer.parseInt(maxGearsInput.getText());

            SkrzyniaBiegow nowaSkrzynia = new SkrzyniaBiegow(gearName, gearPrice, gearWeight, 0, maxGears);

            // 5. Tworzenie SPRZĘGŁA (Nazwa, Cena, Waga)
            String clutchName = clutchNameInput.getText();
            double clutchPrice = Double.parseDouble(clutchPriceInput.getText());
            double clutchWeight = Double.parseDouble(clutchWeightInput.getText());

            Sprzeglo noweSprzeglo = new Sprzeglo(clutchName, clutchPrice, clutchWeight);

            // 6. Finalne utworzenie samochodu
            // Domyślny obrazek, bo nie mamy pola input na ścieżkę (można dodać w przyszłości)
            String domyslnaSciezka = "/images/car.png";

            Samochod nowySamochod = new Samochod(model, registration, carWeight,
                    nowySilnik, nowaSkrzynia, noweSprzeglo, domyslnaSciezka);

            // 7. Dodanie do głównego kontrolera
            HelloController.addCarToMapAndList(nowySamochod);

            // 8. Zamknięcie okna
            Stage stage = (Stage) modelInput.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.err.println("Błąd danych: Upewnij się, że w polach liczbowych (Cena, Waga, RPM) są poprawne liczby.");
            // Opcjonalnie: Wyświetl tutaj Alert (okienko z błędem)
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            e.printStackTrace();
        }
    }
}