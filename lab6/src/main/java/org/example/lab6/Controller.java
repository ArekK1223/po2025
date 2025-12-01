package org.example.lab6;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Controller {

    // --- Deklaracja kontrolek (zgodnie z konwencją nazewnictwa) ---
    @FXML private ComboBox<String> carComboBox;

    @FXML private TextField modelTextField;
    @FXML private TextField plateTextField;

    @FXML private Button startButton;
    @FXML private Button stopButton;

    @FXML private TextField gearTextField;
    @FXML private Button gearUpButton;
    @FXML private Button gearDownButton;

    @FXML private TextField rpmTextField;

    @FXML private ImageView carImageView;

    // --- Metoda uruchamiana automatycznie po załadowaniu okna ---
    @FXML
    public void initialize() {
        System.out.println("Inicjalizacja kontrolera...");

        // Konfiguracja listy samochodów (IV.2)
        ObservableList<String> cars = FXCollections.observableArrayList(
                "Fiat 126p", "Ford Mustang", "Audi A4", "Toyota Yaris"
        );
        carComboBox.setItems(cars);
    }

    // --- Metody obsługi zdarzeń (III.3) ---

    @FXML
    private void onStartButton() {
        System.out.println("Samochód uruchomiony!");
        // Tutaj można dodać logikę, np. zmiana koloru tła albo włączenie silnika w modelu
        rpmTextField.setText("800"); // Ustawienie obrotów jałowych
    }

    @FXML
    private void onStopButton() {
        System.out.println("Samochód wyłączony!");
        rpmTextField.setText("0");
    }

    @FXML
    private void onGearUpButton() {
        System.out.println("Zwiększono bieg");
        // Prosta symulacja zmiany tekstu
        String currentGear = gearTextField.getText();
        if (currentGear.equals("N")) gearTextField.setText("1");
        else if (currentGear.equals("1")) gearTextField.setText("2");
        // itd...
    }

    @FXML
    private void onGearDownButton() {
        System.out.println("Zmniejszono bieg");
        gearTextField.setText("N");
    }

    @FXML
    private void onCarSelection() {
        // Pobranie wybranej wartości z ComboBox
        String selectedCar = carComboBox.getValue();
        System.out.println("Wybrano samochód: " + selectedCar);

        // Aktualizacja pola modelTextField
        if (selectedCar != null) {
            modelTextField.setText(selectedCar);
        }
    }
}