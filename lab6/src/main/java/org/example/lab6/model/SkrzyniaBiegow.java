package org.example.lab6.model;

public class SkrzyniaBiegow {
    public int aktualnyBieg;
    public int iloscBiegow;

    public SkrzyniaBiegow(int aktualnyBieg, int iloscBiegow) {
        this.aktualnyBieg = aktualnyBieg;
        this.iloscBiegow = iloscBiegow;
    }
    public void zwiekszBieg() {
        if (aktualnyBieg < iloscBiegow) {
            aktualnyBieg ++;
        }
    }
    public void zmniejszBieg() {
        if (aktualnyBieg > 0) {
            aktualnyBieg --;
        }
    }
    public void ustawBieg(int bieg) {
        if (bieg >= 0 && bieg <= iloscBiegow) {
            this.aktualnyBieg = bieg;
        } else {
            System.out.println("Nieprawidłowy bieg: " + bieg);
        }
    }
    public int getAktualnyBieg() {
        return aktualnyBieg;
    }
}

