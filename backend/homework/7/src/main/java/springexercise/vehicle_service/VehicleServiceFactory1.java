package springexercise.vehicle_service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import springexercise.model.Speaker;
import springexercise.model.Tyre;
import springexercise.service.InventoryStore;
import springexercise.service.VehicleService;
import java.util.List;

@Qualifier("factory1")
public class VehicleServiceFactory1 extends VehicleService {
    public VehicleServiceFactory1(List<Tyre> tyres, List<Speaker> speakers, String location) {
        super(tyres, speakers, location);
    }

    void generateVehicles()
    {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(InventoryStore.class);
        InventoryStore inventoryStore1 = context.getBean("factory1",InventoryStore.class);
        inventoryStore1.generateVehicle("factory1");
    }
}
