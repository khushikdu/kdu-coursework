package springexercise.service;
import java.util.ArrayList;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import springexercise.model.Vehicle;
import java.util.List;
@Configuration
public abstract class VehicleService {
    @Autowired
    private  List<Tyre> tyres;
    @Autowired
    private  List<Speaker> speakers;
    private String location;

    protected VehicleService(List<Tyre> tyres, List<Speaker> speakers, String location) {
        this.tyres = tyres;
        this.speakers = speakers;
        this.location = location;
    }

}
