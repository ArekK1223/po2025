module org.example.lab6 {
    requires javafx.controls;
    requires javafx.fxml;

    // Musisz otworzyć pakiet, w którym jest HelloController dla FXML
    opens org.example.lab6 to javafx.fxml;
    // Jeśli masz modele w osobnym pakiecie, je też otwórz
    opens org.example.lab6.model to javafx.fxml;

    exports org.example.lab6;
}