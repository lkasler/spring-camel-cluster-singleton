package hu.bridgesoft.camel.leader;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class ClusterRoutes {
    @Component
    public static class Routes extends RouteBuilder {
        public void configure() throws Exception {

            from("master:lock1:timer:clock")
              .log("Hello World!");

        }
    }
}
