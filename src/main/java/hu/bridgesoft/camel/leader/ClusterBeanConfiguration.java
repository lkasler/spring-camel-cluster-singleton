package hu.bridgesoft.camel.leader;

import org.apache.camel.cluster.CamelClusterEventListener;
import org.apache.camel.cluster.CamelClusterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClusterBeanConfiguration {
    @Configuration
    public static class BeanConfiguration {

        @Bean
        public CustomClusterSingletonService customService(CamelClusterService clusterService) throws Exception {
            CustomClusterSingletonService service = new CustomClusterSingletonService();
            clusterService.getView("lock2").addEventListener((CamelClusterEventListener.Leadership) (view, leader) -> {
                boolean weAreLeaders = leader.isPresent() && leader.get().isLocal();
                if (weAreLeaders && !service.isStarted()) {
                    service.start();
                } else if (!weAreLeaders && service.isStarted()) {
                    service.stop();
                }
            });
            return service;
        }

    }
}
