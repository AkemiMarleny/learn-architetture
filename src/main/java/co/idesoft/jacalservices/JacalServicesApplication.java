package co.idesoft.jacalservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "co.idesoft.jacalservices",
})
public class JacalServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(JacalServicesApplication.class, args);
    }

}
