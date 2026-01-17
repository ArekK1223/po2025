package org.example.lab6.model;

import java.util.ArrayList;
import java.util.List;

// 1. Klasa dziedziczy po Thread [cite: 23]
public class Samochod extends Thread {
    private String model;
    private String nrRejestracyjny;
    private double waga;
    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Sprzeglo sprzeglo;

    // Pola do obsługi ruchu
    private Pozycja pozycja; // [cite: 24]
    private Pozycja cel;
    private final double predkoscRuchu = 50.0; // Przykładowa prędkość pikseli/s

    // Lista słuchaczy (Obserwator) [cite: 103]
    private List<Listener> listeners = new ArrayList<>();
    private String sciezkaDoObrazka;

    public Samochod(String model, String nrRejestracyjny, double waga, Silnik silnik, SkrzyniaBiegow skrzyniaBiegow, Sprzeglo sprzeglo, String sciezkaDoObrazka) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.silnik = silnik;
        this.skrzyniaBiegow = skrzyniaBiegow;
        this.sprzeglo = sprzeglo;
        this.sciezkaDoObrazka = sciezkaDoObrazka;

        // Domyślna pozycja startowa
        this.pozycja = new Pozycja(0, 0);

        // Uruchomienie wątku na końcu konstruktora [cite: 26]
        start();
    }
    public String getSciezkaDoObrazka() {
        return sciezkaDoObrazka;
    }

    // Konstruktor bezargumentowy (dla kompatybilności z Twoim kodem)
    public Samochod() {
        this("Nieznany", "BRAK", 1000, new Silnik(6000, 0), new SkrzyniaBiegow(0, 5), new Sprzeglo(), "/images/car.png");
    }

    // 2. Metoda ustawiająca cel podróży [cite: 25]
    public void jedzDo(Pozycja nowaPozycja) {
        this.cel = nowaPozycja;
        System.out.println("Jadę do: " + cel);
    }

    public Pozycja getPozycja() {
        return pozycja;
    }

    // 3. Logika wątku (przemieszczanie) [cite: 27, 30]
    @Override
    public void run() {
        double dt = 0.1; // krok czasowy (100ms = 0.1s) [cite: 29]

        while (true) {
            try {
                if (cel != null) {
                    double dx = cel.getX() - pozycja.getX();
                    double dy = cel.getY() - pozycja.getY();
                    double odleglosc = Math.sqrt(dx * dx + dy * dy); // [cite: 32]

                    if (odleglosc > 1.0) { // Jeśli jeszcze nie dotarliśmy
                        // Obliczenie przesunięcia [cite: 36, 40]
                        double moveX = predkoscRuchu * dt * (dx / odleglosc);
                        double moveY = predkoscRuchu * dt * (dy / odleglosc);

                        // Aktualizacja pozycji
                        pozycja = new Pozycja(pozycja.getX() + moveX, pozycja.getY() + moveY);
                    } else {
                        // Dotarliśmy do celu
                        pozycja = cel;
                        cel = null;
                    }
                    // Powiadomienie widoku o zmianie pozycji [cite: 115]
                    notifyListeners();
                }

                Thread.sleep(100); // Czekaj 100ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // 4. Implementacja wzorca Obserwator [cite: 104, 107, 110]
    public void addListener(Listener listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener listener) {
        listeners.remove(listener);
    }

    private void notifyListeners() {
        for (Listener listener : listeners) {
            listener.update();
        }
    }

    // Gettery (istniejące)
    public Silnik getSilnik() { return silnik; }
    public SkrzyniaBiegow getSkrzyniaBiegow() { return skrzyniaBiegow; }
    public Sprzeglo getSprzeglo() { return sprzeglo; }
    public String getModel() { return model; }
    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public double getWaga() { return waga; }
}