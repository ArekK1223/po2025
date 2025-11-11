package symulator;

public abstract class Komponent {
    public String producent;
    public String model;

    public String getProducent() {
        return producent;
    }
    public String getModel() {
        return model;
    }
    public void setProducent(String producent) {
        this.producent = producent;
    }
    public void setModel(String model) {
        this.model = model;
    }

}