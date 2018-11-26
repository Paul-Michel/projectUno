package uno.engine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(EngineApplication.class, args);
    }

}
