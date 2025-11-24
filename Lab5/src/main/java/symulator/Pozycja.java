package symulator;

public class Pozycja {
    private double x;
    private double y;

    public Pozycja(double x, double y) {
        this.y = y;
        this.x = x;
    }
    public void aktualizujPozycje(double deltaX, double deltaY) {
        this.x += deltaX;
        this.y += deltaY;
    }
    public String getPozycja() {
        return "(" + x + ", " + y + ")";
    }
    public void przeniesc(Punkt cel, double V, double dt) {
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


}

//V-predkosc
//dt- czas kroku

