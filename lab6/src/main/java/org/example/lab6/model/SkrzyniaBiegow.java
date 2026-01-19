package org.example.lab6.model;

public class SkrzyniaBiegow {
    // Pola skrzyni biegów
    private String nazwa;
    private double cena;
    private double waga;

    private int aktualnyBieg;
    private int iloscBiegow;

    // KONSTRUKTOR PEŁNY (z nazwą, ceną, wagą)
    public SkrzyniaBiegow(String nazwa, double cena, double waga, int aktualnyBieg, int iloscBiegow) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.waga = waga;
        this.aktualnyBieg = aktualnyBieg;
        this.iloscBiegow = iloscBiegow;
    }

    // KONSTRUKTOR UPROSZCZONY (dla kompatybilności ze starym kodem)
    // Ustawia domyślną nazwę, żeby uniknąć błędów
    public SkrzyniaBiegow(int aktualnyBieg, int iloscBiegow) {
        this("Skrzynia Standard", 0.0, 0.0, aktualnyBieg, iloscBiegow);
    }

    // Logika zmiany biegów
    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg++;
            System.out.println("Skrzynia: Bieg " + aktualnyBieg);
        }
    }

    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg--;
            System.out.println("Skrzynia: Bieg " + aktualnyBieg);
        }
    }

    public void ustawBieg(int bieg) {
        if (bieg >= 0 && bieg <= iloscBiegow) {
            this.aktualnyBieg = bieg;
        } else {
            System.out.println("Nieprawidłowy bieg: " + bieg);
        }
    }

    // Gettery
    public int getAktualnyBieg() { return aktualnyBieg; }
    public int getIloscBiegow() { return iloscBiegow; }
    public String getNazwa() { return nazwa; }
    public double getCena() { return cena; }
    public double getWaga() { return waga; }
}
