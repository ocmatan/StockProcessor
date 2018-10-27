package stockProcessor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class StockProcessorApplication {

    public static void main(String[] args){
        SpringApplication.run(StockProcessorApplication.class, args);
        System.out.println("application is starting");

    }

}
