package co.idesoft.architetture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "co.idesoft.architetture",
})
public class ArchitettureApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArchitettureApplication.class, args);
    }

}
