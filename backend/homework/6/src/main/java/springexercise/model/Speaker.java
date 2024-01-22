package springexercise.model;

public class Speaker {
    private String name;
    private double price;

    public Speaker(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }


    @Override
    public String toString() {
        return "Speaker{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
