package springexercise.service;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import springexercise.model.Vehicle;
import java.util.List;
@Service
public class VehicleService {
    @Autowired
    private  final List<Tyre> tyres=new ArrayList<>();
    @Autowired
    private  final List<Speaker> speakers=new ArrayList<>();

    @PostConstruct
    public List<Vehicle> generateVehicle(){

        List<Vehicle> vehicles=new ArrayList<>();
        for (Tyre tyre:tyres) {
            for (Speaker speaker:speakers) {
                Vehicle vehicle=new Vehicle(tyre,speaker);
                vehicles.add(vehicle);
            }
        }
        return vehicles;
    }
    @Bean
    public Vehicle findExpensiveVehicle(){
        List<Vehicle> vehicles=generateVehicle();
        return vehicles.stream()
                .max((v1,v2)->Double.compare(v1.generatePrice(),v2.generatePrice()))
                .orElse(null);
    }
}
