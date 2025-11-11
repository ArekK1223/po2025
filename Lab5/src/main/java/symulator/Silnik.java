package symulator;

public class Silnik {
    public int maxObroty;
    public int obroty;

    public Silnik( int maxObroty, int obroty) {
        this.maxObroty = maxObroty;
        this.obroty = obroty;
    }
    public void uruchom() {
        this.obroty = 800;
    }
    public void zatrzymaj() {
        this.obroty = 0;
    }
}
