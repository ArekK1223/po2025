package org.example.lab6.model; // Upewnij się, że pakiet jest zgodny z Twoim projektem

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // --- Dodane gettery wymagane przez GUI ---
    public double getX() { return x; }
    public double getY() { return y; }

    // Twoja metoda, lekko zmodyfikowana by przyjmowała Pozycję jako cel
    public void przeniesc(Pozycja cel, double V, double dt) {
        double dx = cel.x - this.x;
        double dy = cel.y - this.y;
        double odleglosc = Math.sqrt(dx * dx + dy * dy);

        if (odleglosc > 0) {
            double krok = V * dt;

            if (krok >= odleglosc) {
                this.x = cel.x;
                this.y = cel.y;
            } else {
                this.x += (dx / odleglosc) * krok;
                this.y += (dy / odleglosc) * krok;
            }
        }
    }

    // Metoda pomocnicza, by sprawdzić czy dotarliśmy do celu
    public boolean czyDotarlem(Pozycja cel) {
        if (cel == null) return true;
        double dx = cel.x - this.x;
        double dy = cel.y - this.y;
        return Math.sqrt(dx * dx + dy * dy) < 1.0; // Tolerancja 1 piksela
    }

    @Override
    public String toString() {
        return "(" + String.format("%.2f", x) + ", " + String.format("%.2f", y) + ")";
    }
}

