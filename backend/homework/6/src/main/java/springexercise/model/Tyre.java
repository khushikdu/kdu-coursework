package springexercise.model;

public class Tyre {
    private String name;
    private double price;

    public Tyre(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Tyre{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
