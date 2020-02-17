package hu.bridgesoft.camel.leader;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClusterRoutes extends RouteBuilder {
        public void configure() throws Exception {
            from("master:lock1:timer://singleton?fixedRate=true&period=10s")
              .bean(WorkerLogic.class, "clusterWork");
    }
}
