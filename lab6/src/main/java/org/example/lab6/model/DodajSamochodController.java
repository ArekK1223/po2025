package org.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage; // Konieczne do zamknięcia okna

import org.example.lab6.model.Silnik;
import org.example.lab6.model.SkrzyniaBiegow;
import org.example.lab6.model.Sprzeglo;
import org.example.lab6.model.Samochod;

public class DodajSamochodController {

    // Pola muszą pasować do fx:id w DodajSamochod.fxml
    @FXML private TextField modelInput;
    @FXML private TextField plateInput;
    @FXML private TextField weightInput;

    // Przykładowe pola dla konfiguracji komponentów
    @FXML private TextField maxRpmInput;
    @FXML private TextField maxGearsInput;

    // W DodajSamochodController.java (dodaj tę metodę)
    @FXML
    private void onCancelButton() {
        // Zamknięcie okna
        Stage stage = (Stage) modelInput.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onDodajButton() {
        try {
            // 1. Zbieranie i walidacja danych (upewnienie się, że pola nie są puste)
            String model = modelInput.getText().trim();
            String registration = plateInput.getText().trim();

            if (model.isEmpty() || registration.isEmpty()) {
                System.err.println("Błąd: Model i nr rejestracyjny nie mogą być puste.");
                return;
            }

            // 2. Parsowanie liczb (używamy wartości domyślnych, jeśli pola są puste)
            double weight = weightInput.getText().trim().isEmpty() ? 1000.0 : Double.parseDouble(weightInput.getText());
            int maxRpm = maxRpmInput.getText().trim().isEmpty() ? 7000 : Integer.parseInt(maxRpmInput.getText());
            int maxGears = maxGearsInput.getText().trim().isEmpty() ? 6 : Integer.parseInt(maxGearsInput.getText());

            // 3. Tworzenie komponentów
            Silnik nowySilnik = new Silnik(maxRpm, 0);
            SkrzyniaBiegow nowaSkrzynia = new SkrzyniaBiegow(0, maxGears);
            Sprzeglo noweSprzeglo = new Sprzeglo();

            // 4. Tworzenie obiektu Samochod (Zakładamy, że konstruktor Samochod jest poprawny)
            Samochod nowySamochod = new Samochod(model, registration, weight,
                    nowySilnik, nowaSkrzynia, noweSprzeglo);

            // 5. KLUCZOWE: Wywołanie statycznej metody w HelloController
            HelloController.addCarToMapAndList(nowySamochod);

            // 6. Zamknięcie okna (Stage)
            Stage stage = (Stage) modelInput.getScene().getWindow();
            stage.close();

        } catch (NumberFormatException e) {
            System.err.println("Błąd formatu: Upewnij się, że Waga, Max RPM i Max Bieg są poprawnymi liczbami.");
        } catch (Exception e) {
            System.err.println("Wystąpił nieoczekiwany błąd podczas dodawania samochodu: " + e.getMessage());
        }
    }
}