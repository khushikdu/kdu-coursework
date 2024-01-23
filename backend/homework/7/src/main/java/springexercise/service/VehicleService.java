package springexercise.service;

import springexercise.model.Speaker;
import springexercise.model.Tyre;
import java.util.List;

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