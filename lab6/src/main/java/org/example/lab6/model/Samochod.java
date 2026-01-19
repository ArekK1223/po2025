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
    public double getAktualnaPredkosc() {
        // 1. Obejście problemu ze sprzęgłem bez zmieniania jego klasy:
        // Sprawdzamy, czy opis tekstowy sugeruje, że jest wciśnięte.
        String stan = sprzeglo.getStanTekstowy();
        if (stan != null && (stan.contains("Wciśnięte") || stan.contains("rozłączone"))) {
            return 0.0; // Sprzęgło wciśnięte = brak napędu
        }

        // 2. Pobieramy dane
        double obroty = silnik.getObroty();
        double bieg = skrzyniaBiegow.getAktualnyBieg();

        // 3. Najprostszy wzór na prędkość:
        // (Obroty * Bieg) / stała kalibracyjna (żeby auto nie latało jak rakieta)
        // Im mniejszy dzielnik (400.0), tym szybciej auto pojedzie.
        return (obroty * bieg) / 400.0;
    }

    // 3. Logika wątku (przemieszczanie) [cite: 27, 30]
    @Override
    public void run() {
        while (true) { // Pętla nieskończona
            try {
                // 1. Obliczamy aktualną prędkość (to ta nowa metoda wyżej)
                double v = getAktualnaPredkosc();

                // 2. Jeśli mamy cel I samochód ma prędkość (dodano gazu i wrzucono bieg)
                if (cel != null && v > 0) {
                    double dx = cel.getX() - pozycja.getX();
                    double dy = cel.getY() - pozycja.getY();
                    double odleglosc = Math.sqrt(dx * dx + dy * dy);

                    if (odleglosc > 5.0) { // Zwiększyłem margines do 5 pikseli, żeby nie drgał
                        // Przesuwamy się o "v" w stronę celu
                        double moveX = v * (dx / odleglosc);
                        double moveY = v * (dy / odleglosc);

                        pozycja = new Pozycja(pozycja.getX() + moveX, pozycja.getY() + moveY);
                    } else {
                        // Dojechaliśmy
                        pozycja = cel;
                        cel = null;
                    }
                }

                // 3. Ważne: Odśwież widok
                notifyListeners();

                // 4. Klatkaż (opóźnienie)
                Thread.sleep(50); // Mniej niż 100ms dla płynniejszego ruchu
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