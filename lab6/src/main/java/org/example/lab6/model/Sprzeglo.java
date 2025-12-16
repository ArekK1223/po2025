package org.example.lab6.model;

public class Sprzeglo {

    private boolean wcisniete;

    public Sprzeglo() {

        this.wcisniete = false;
    }
    public void wcisnij() {
        this.wcisniete = true;
        System.out.println("Sprzęgło: WCIŚNIĘTE (Napęd rozłączony)");
    }
    public void zwolnij() {
        this.wcisniete = false;
        System.out.println("Sprzęgło: ZWOLNIONE (Napęd połączony)");
    }
    public boolean getWcisniete() {
        return wcisniete;
    }
    public String getStanTekstowy() {
        return this.wcisniete ? "Wciśnięte" : "Zwolnione";
    }
}
