import animals.Dog;
import animals.Parrot;
import animals.Animal;
import animals.Snake;

import java.util.Random;


public class Zoo {
    Animal[] animals = new Animal[100];
    private Random random = new Random();

    public void fillWithRandomAnimals() {
        for (int i = 0; i < animals.length; i++) {  // POPRAWKA: i < animals.length
            int animalType = random.nextInt(3);
            switch (animalType) {
                case 0:
                    animals[i] = new Dog("Pies_" + i);
                    break;
                case 1:
                    animals[i] = new Parrot("Papuga_" + i);
                    break;
                case 2:
                    animals[i] = new Snake("Wąż_" + i);
                    break;
            }
        }
    }
    public int countLegs() {
        int legsCounter = 0;
        for (int i = 0; i < animals.length; i++) {
            if (animals[i] != null) {
                if (animals[i] instanceof Dog) {
                    legsCounter += 4; // Pies ma 4 nogi
                } else if (animals[i] instanceof Parrot) {
                    legsCounter += 2; // Papuga ma 2 nogi
                } else if (animals[i] instanceof Snake) {
                    legsCounter += 0; // Wąż nie ma nóg
                }
            }
        }
        return legsCounter;
    }
    public static void main(String[] args) {
        Zoo zoo = new Zoo();


        zoo.fillWithRandomAnimals();


        int totalLegs = zoo.countLegs();
        System.out.println("Suma nóg wszystkich zwierząt: " + totalLegs);

        int animalCount = 0;
        for (Animal animal : zoo.animals) {
            if (animal != null) animalCount++;
        }
        System.out.println("Liczba zwierząt: " + animalCount);
    }
    public void displayAnimalDescriptions() {
        for (Animal animal : animals) {
            if (animal != null) {
                String description = animal.getDescription();
                System.out.println(description);
            }
        }
    }

}

