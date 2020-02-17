package hu.bridgesoft.camel.leader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class WorkerLogic {
    private static final Logger  LOGGER = LoggerFactory.getLogger(WorkerLogic.class);

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String serverPort;


    public void clusterWork(){
        LOGGER.info("Cluster worker running at app: {} from port: {}", appName, serverPort);
    }




}
