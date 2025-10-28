package animals;

public class Animal {
    protected String name;
    protected int legs;

    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }


    public String getDescription() {
        return "Zwierzę: " + name + ", liczba nóg: " + legs;
    }
}