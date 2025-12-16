package org.example.lab6.model;

public class Samochod {

    private String model;
    private String nrRejestracyjny;
    private double waga;
    private int predkosc;

    private Silnik silnik;
    private SkrzyniaBiegow skrzyniaBiegow;
    private Sprzeglo sprzeglo;


    public Samochod() {
        this.model = "Domyślny";
        this.nrRejestracyjny = "BRAK";
        this.waga = 0.0;
        this.predkosc = 0;

        this.silnik = new Silnik(6000, 0);
        this.skrzyniaBiegow = new SkrzyniaBiegow(0, 5);
        this.sprzeglo = new Sprzeglo();
    }


    public Samochod(String model, String nrRejestracyjny, double waga,
                    Silnik silnik, SkrzyniaBiegow skrzyniaBiegow, Sprzeglo sprzeglo) {
        this.model = model;
        this.nrRejestracyjny = nrRejestracyjny;
        this.waga = waga;
        this.predkosc = 0;

        this.silnik = silnik;
        this.skrzyniaBiegow = skrzyniaBiegow;
        this.sprzeglo = sprzeglo;
    }


    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getNrRejestracyjny() { return nrRejestracyjny; }
    public void setNrRejestracyjny(String nrRejestracyjny) { this.nrRejestracyjny = nrRejestracyjny; }

    public double getWaga() { return waga; }
    public void setWaga(double waga) { this.waga = waga; }

    public int getPredkosc() { return predkosc; }
    public void setPredkosc(int predkosc) { this.predkosc = predkosc; }


    public Silnik getSilnik() {
        return silnik;
    }

    public SkrzyniaBiegow getSkrzyniaBiegow() {
        return skrzyniaBiegow;
    }

    public Sprzeglo getSprzeglo() {
        return sprzeglo;
    }

    @Override
    public String toString() {
        return model + " (" + nrRejestracyjny + ")";
    }
}
