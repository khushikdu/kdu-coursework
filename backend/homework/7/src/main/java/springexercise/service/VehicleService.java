package springexercise.service;
import org.springframework.context.annotation.Configuration;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import java.util.List;
@Configuration
public abstract class VehicleService {
    private final List<Tyre> tyres;
    private final List<Speaker> speakers;
    private String location;

    protected VehicleService(List<Tyre> tyres, List<Speaker> speakers, String location) {
        this.tyres = tyres;
        this.speakers = speakers;
        this.location = location;
    }
}