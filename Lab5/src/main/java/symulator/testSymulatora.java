package symulator;

public class testSymulatora {
    public static void main(String[] args) {

        Silnik silnik = new Silnik(6000, 100);
        SkrzyniaBiegow skrzynia = new SkrzyniaBiegow(0, 5);
        Sprzeglo sprzeglo = new Sprzeglo(false);
        Samochod samochod = new Samochod(silnik, skrzynia);


        samochod.wlacz();
        System.out.println("Stan po włączeniu:");
        wyswietlStan(silnik, skrzynia, sprzeglo);


        sprzeglo.wcisnij();
        System.out.println("\nPo wciśnięciu sprzęgła:");
        wyswietlStan(silnik, skrzynia, sprzeglo);

        skrzynia.zwiekszBieg();
        System.out.println("\nPo zwiększeniu biegu:");
        wyswietlStan(silnik, skrzynia, sprzeglo);


        sprzeglo.zwolnij();
        System.out.println("\nPo zwolnieniu sprzęgła:");
        wyswietlStan(silnik, skrzynia, sprzeglo);

        sprzeglo.wcisnij();
        skrzynia.zmniejszBieg();
        System.out.println("\nPo zmniejszeniu biegu:");
        wyswietlStan(silnik, skrzynia, sprzeglo);


        samochod.wylacz();
        System.out.println("\nPo wyłączeniu:");
        wyswietlStan(silnik, skrzynia, sprzeglo);
    }

    public static void wyswietlStan(Silnik silnik, SkrzyniaBiegow skrzynia, Sprzeglo sprzeglo) {
        System.out.println("Obroty silnika: " + silnik.obroty);
        System.out.println("Aktualny bieg: " + skrzynia.aktualnyBieg);
        System.out.println("Sprzęgło wciśnięte: " + sprzeglo.stanSprzegla);
    }

}
