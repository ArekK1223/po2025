package symulator;

public class Sprzeglo {
    public boolean stanSprzegla;

    public Sprzeglo(boolean stanSprzegla) {
        this.stanSprzegla = stanSprzegla;
    }
    public void wcisnij() {
        stanSprzegla = true;
    }
    public void zwolnij() {
        stanSprzegla = false;
    }
}
