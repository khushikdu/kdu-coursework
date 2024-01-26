package springexercise.model;
import org.springframework.stereotype.Component;

@Component
public class Vehicle {
    private Tyre tyre;
    private Speaker speaker;
    private double price;
    public Vehicle(Tyre tyre, Speaker speaker) {
        this.tyre = tyre;
        this.speaker = speaker;
        this.price=generatePrice();
    }
    public double generatePrice()
    {
        return tyre.getPrice()+speaker.getPrice();

    }
    @Override
    public String toString() {
        return "Vehicle{" +
                "\ntyre=" + tyre +
                ", \nspeaker=" + speaker +
                ", \nprice=" + price +
                '}';
    }
}