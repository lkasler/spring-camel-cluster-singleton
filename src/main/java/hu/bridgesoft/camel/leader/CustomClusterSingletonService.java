package hu.bridgesoft.camel.leader;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomClusterSingletonService {

    private static final Logger LOG = LoggerFactory.getLogger(CustomClusterSingletonService.class);

    private boolean started;

    public void start() {
        LOG.info("CustomService has been started on the master pod");
        started = true;
    }

    public boolean isStarted() {
        return started;
    }

    public void stop() {
        LOG.info("CustomService has been stopped on the master pod");
        started = false;
    }

}
