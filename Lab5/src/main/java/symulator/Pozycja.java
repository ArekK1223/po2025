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
}
