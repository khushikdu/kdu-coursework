package springexercise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import springexercise.model.Vehicle;

import java.util.ArrayList;
import java.util.List;

@Component
public class InventoryStore {
    private List<Vehicle> vehicles;
    @Autowired
    private  List<Tyre> tyres;
    @Autowired
    private  List<Speaker> speakers;

    public void generateVehicle(String factoryID){
        vehicles=new ArrayList<>();
        for (Tyre tyre:tyres) {
            for (Speaker speaker:speakers) {
                Vehicle vehicle=new Vehicle(tyre,speaker);
                if(factoryID.equalsIgnoreCase("factory1")) {
                    vehicle.setPrice(1.1 * vehicle.getPrice());
                } else if (factoryID.equalsIgnoreCase("factory2")) {
                    vehicle.setPrice(1.2 * vehicle.getPrice());
                }
                vehicles.add(vehicle);
            }
        }
    }

    @Bean("factory1")
    InventoryStore inventoryStore1()
    {
        return new InventoryStore();
    }
    @Bean("factory2")
    InventoryStore inventoryStore2()
    {
        return new InventoryStore();
    }

    public Vehicle findExpensiveVehicle(@Qualifier("factory1")String factory){
        generateVehicle(factory);
        return vehicles.stream()
                .max((v1,v2)->Double.compare(v1.generatePrice(),v2.generatePrice()))
                .orElse(null);
    }
    public Vehicle findCheapestVehicle(@Qualifier("factory2")String factory){
        generateVehicle(factory);
        return vehicles.stream()
                .min((v1,v2)->Double.compare(v1.generatePrice(),v2.generatePrice()))
                .orElse(null);
    }
}