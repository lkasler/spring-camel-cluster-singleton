package hu.bridgesoft.camel.leader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClusterSingletonApp {

    public static void main(String[] args) {
        SpringApplication.run(ClusterSingletonApp.class, args);
    }

}
