package springexercise.vehicle_service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import springexercise.service.InventoryStore;
import springexercise.service.VehicleService;

import java.util.List;
@Qualifier("factory2")
public class VehicleServiceFactory2 extends VehicleService {
    public VehicleServiceFactory2(List<Tyre> tyres, List<Speaker> speakers, String location) {
        super(tyres, speakers, location);
    }
    void generateVehicles()
    {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(InventoryStore.class);
        InventoryStore inventoryStore1 = (InventoryStore) context.getBean("factory2");
        inventoryStore1.generateVehicle("factory2");
    }
}
