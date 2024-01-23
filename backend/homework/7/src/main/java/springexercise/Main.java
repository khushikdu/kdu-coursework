package springexercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springexercise.config.AppConfig;
import springexercise.logging.Logging;
import springexercise.model.Vehicle;
import springexercise.service.InventoryStore;
import springexercise.service.VehicleService;

public class Main {
    public static void main(String[] args) {
        Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        InventoryStore vehicleService = context.getBean(InventoryStore.class);
        Vehicle expensiveVehicle = vehicleService.findExpensiveVehicle();
        Logging.printLogger("Most expensive vehicle "+expensiveVehicle, loggerTypeInfo);
    }
}
