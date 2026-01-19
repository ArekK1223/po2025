package org.example.lab6;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.lab6.model.Samochod;
import org.example.lab6.model.Silnik;
import org.example.lab6.model.SkrzyniaBiegow;
import org.example.lab6.model.Sprzeglo;

import java.util.HashMap;
import java.util.Map;

public class DodajSamochodController {

    @FXML private TextField modelInput;
    @FXML private TextField plateInput;
    @FXML private TextField weightInput;

    // Pole dla ComboBoxa z FXML
    @FXML private ComboBox<String> engineComboBox;

    // Pozostałe komponenty (uproszczone dla przykładu)
    @FXML private TextField gearboxNameInput;
    @FXML private TextField gearboxPriceInput;
    @FXML private TextField gearboxWeightInput;
    @FXML private TextField maxGearsInput;

    @FXML private TextField clutchNameInput;
    @FXML private TextField clutchPriceInput;
    @FXML private TextField clutchWeightInput;

    // Mapa przechowująca definicje dostępnych silników
    private Map<String, Silnik> bibliotekaSilnikow = new HashMap<>();

    @FXML
    public void initialize() {
        // Tworzymy bazę dostępnych silników
        Silnik s1 = new Silnik("1.2 Fire (Eco)", 3000.0, 100.0, 5500, 0);
        Silnik s2 = new Silnik("2.0 TDI (Diesel)", 15000.0, 180.0, 6000, 0);
        Silnik s3 = new Silnik("4.0 V8 (Sport)", 45000.0, 250.0, 8500, 0);

        bibliotekaSilnikow.put(s1.getNazwa(), s1);
        bibliotekaSilnikow.put(s2.getNazwa(), s2);
        bibliotekaSilnikow.put(s3.getNazwa(), s3);

        // Wypełniamy ComboBox nazwami z mapy
        engineComboBox.getItems().addAll(bibliotekaSilnikow.keySet());
        engineComboBox.getSelectionModel().selectFirst();
    }

    @FXML
    private void onDodajButton() {
        try {
            // 1. Pobieramy dane auta
            String model = modelInput.getText();
            String plate = plateInput.getText();
            double weight = Double.parseDouble(weightInput.getText());

            // 2. Pobieramy wybrany silnik z mapy
            String wybrano = engineComboBox.getValue();
            Silnik wybranySilnik = bibliotekaSilnikow.get(wybrano);

            // 3. Pobieramy dane skrzyni (z pól tekstowych)
            SkrzyniaBiegow sb = new SkrzyniaBiegow(
                    gearboxNameInput.getText(),
                    Double.parseDouble(gearboxPriceInput.getText()),
                    Double.parseDouble(gearboxWeightInput.getText()),
                    0,
                    Integer.parseInt(maxGearsInput.getText())
            );

            // 4. Pobieramy dane sprzęgła
            Sprzeglo sp = new Sprzeglo(
                    clutchNameInput.getText(),
                    Double.parseDouble(clutchPriceInput.getText()),
                    Double.parseDouble(clutchWeightInput.getText())
            );

            // 5. Tworzymy obiekt samochodu (możesz tu dodać wybór obrazka, np. domyślny)
            Samochod nowy = new Samochod(model, plate, weight, wybranySilnik, sb, sp, "/images/car.png");

            // 6. Wysyłamy do głównego kontrolera
            HelloController.addCarToMapAndList(nowy);

            // 7. Zamykamy okno
            closeWindow();

        } catch (NumberFormatException e) {
            System.err.println("Błąd: Sprawdź czy pola liczbowe są poprawne!");
        }
    }

    @FXML
    private void onCancelButton() {
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) modelInput.getScene().getWindow();
        stage.close();
    }
}