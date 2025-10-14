import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Lotto {
    public static void main(String[] args) {
        Random generator = new Random();
        ArrayList<Integer> wylosowane_liczby =  new ArrayList<Integer>();
        int trafienia = 0;
        int liczba_losowan = 0;

        for ( int i = 0; i < 6; i ++) {
            wylosowane_liczby.add(generator.nextInt(50));
        }
        System.out.println("Podaj swoje typy np: java Lotto 1 2 3 4 5 6: ");
        int[] typy = new int[args.length];

        for ( int i = 0; i < 6; i++) {
            typy[i] = Integer.parseInt(args[i]);
        }
        do {
            wylosowane_liczby.clear();
            while (wylosowane_liczby.size() < 6) {
                int liczba = generator.nextInt(49) + 1;
                if (!wylosowane_liczby.contains(liczba)) {
                    wylosowane_liczby.add(liczba);
                }
            }
            trafienia = 0;
            liczba_losowan++;

            for (int i = 0; i < 6; i++) {
                if (wylosowane_liczby.contains(typy[i])) {
                    trafienia++;
                }
            }

        } while (trafienia != 6);
        System.out.println(wylosowane_liczby);
        System.out.println("Twoje typy: " + Arrays.toString(typy));
        System.out.println("Trafienia: " + trafienia);
        System.out.println("Liczba losowan: " + liczba_losowan);


    }
}
