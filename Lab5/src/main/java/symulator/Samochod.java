package symulator;

public class Samochod {
    public Silnik silnik;
    public SkrzyniaBiegow skrzynia;

   public Samochod(Silnik silnik, SkrzyniaBiegow skrzynia) {
       this.silnik = silnik;
       this.skrzynia = skrzynia;
   }
   public void wlacz() {
       silnik.uruchom();
   }
   public void wylacz() {
       silnik.zatrzymaj();
       skrzynia.ustawBieg(0);
   }
}
