package hu.bridgesoft.camel.leader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ClusterSingletonApp {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClusterSingletonApp.class);


    public static void main(String[] args) {
        String selectedPort = SpringBootUtil.setRandomPort(5000, 5500);
        LOGGER.info("Selected app port is: {}", selectedPort);
        SpringApplication.run(ClusterSingletonApp.class, args);
    }

}
