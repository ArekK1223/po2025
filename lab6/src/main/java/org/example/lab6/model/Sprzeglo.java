package org.example.lab6.model;

public class Sprzeglo {
    // Pola
    private String nazwa;
    private double cena;
    private double waga;

    private boolean wcisniete;

    // Konstruktor PEŁNY
    public Sprzeglo(String nazwa, double cena, double waga) {
        this.nazwa = nazwa;
        this.cena = cena;
        this.waga = waga;
        this.wcisniete = false; // Domyślnie zwolnione
    }

    // Konstruktor DOMYŚLNY (dla kompatybilności)
    public Sprzeglo() {
        this("Standardowe", 500.0, 15.0);
    }

    // Metody logiczne
    public void wcisnij() {
        this.wcisniete = true;
        System.out.println("Sprzęgło: Wciśnięte");
    }

    public void zwolnij() {
        this.wcisniete = false;
        System.out.println("Sprzęgło: Zwolnione");
    }

    public boolean isWcisniete() {
        return wcisniete;
    }

    public String getStanTekstowy() {
        return wcisniete ? "Wciśnięte" : "Zwolnione";
    }

    // --- BRAKUJĄCE GETTERY (To naprawi czerwone błędy) ---
    public String getNazwa() {
        return nazwa;
    }

    public double getCena() {
        return cena;
    }

    public double getWaga() {
        return waga;
    }
}