package springexercise;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springexercise.config.AppConfig;
import springexercise.logging.Logging;
import springexercise.model.Vehicle;
import springexercise.service.InventoryStore;

public class Main {
    public static void main(String[] args) {
        Logging.LoggerType loggerTypeInfo = Logging.LoggerType.INFO;
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        InventoryStore vehicleService = (InventoryStore) context.getBean("inventoryStore");
        Vehicle expensiveVehicle = vehicleService.findExpensiveVehicle("factory1");
        Logging.printLogger("\nMost expensive vehicle "+expensiveVehicle, loggerTypeInfo);

        Vehicle cheapestVehicle = vehicleService.findCheapestVehicle("factory2");
        Logging.printLogger("\nCheapest vehicle "+cheapestVehicle, loggerTypeInfo);
    }
}
