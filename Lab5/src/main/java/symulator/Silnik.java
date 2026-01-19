package org;

import org.example.lab6.model.Komponent;

public class Silnik extends Komponent {
    private int obroty;
    private final int maxRpm;
    private boolean uruchomiony;

    public Silnik(String nazwa, double cena, double waga, int maxRpm, int obroty) {
        super(nazwa, cena, waga);
        this.maxRpm = maxRpm;
        this.obroty = obroty;
        this.uruchomiony = false;
    }

    public void uruchom() {
        this.uruchomiony = true;
        this.obroty = 800; // Obroty jałowe po uruchomieniu
        System.out.println("Silnik uruchomiony. Obroty jałowe: " + obroty);
    }

    public void zatrzymaj() {
        this.uruchomiony = false;
        this.obroty = 0;
        System.out.println("Silnik zatrzymany.");
    }

    public void dodajGazu(int oIle) {
        if (uruchomiony) {
            // Logika realistyczna: nie przekraczamy maxRpm
            if (this.obroty + oIle <= maxRpm) {
                this.obroty += oIle;
            } else {
                this.obroty = maxRpm;
                System.out.println("Osiągnięto maksymalne obroty (odcięcie)!");
            }
        }
    }

    public void ujmijGazu(int oIle) {
        if (uruchomiony) {
            // Nie schodzimy poniżej obrotów jałowych, gdy silnik pracuje
            if (this.obroty - oIle >= 800) {
                this.obroty -= oIle;
            } else {
                this.obroty = 800;
            }
        }
    }

    // Gettery i Settery
    public int getObroty() {
        return obroty;
    }

    public int getMaxRpm() {
        return maxRpm;
    }

    public boolean isUruchomiony() {
        return uruchomiony;
    }
}
