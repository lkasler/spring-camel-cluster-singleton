package hu.bridgesoft.camel.leader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.SocketUtils;
import org.springframework.util.StringUtils;

public final class SpringBootUtil {
    final static Logger LOGGER = LoggerFactory.getLogger(SpringBootUtil.class);

    public static String setRandomPort(int minPort, int maxPort) {
        String userDefinedPort = null;
        try {
            userDefinedPort = System.getProperty("server.port", System.getenv("SERVER_PORT"));
            if(StringUtils.isEmpty(userDefinedPort)) {
                int port = SocketUtils.findAvailableTcpPort(minPort, maxPort);
                System.setProperty("server.port", String.valueOf(port));
                LOGGER.info("Random Server Port is set to {}.", port);
                userDefinedPort = Integer.toString(port);
            }
        } catch( IllegalStateException e) {
            LOGGER.warn("No port available in range minPort-maxPort: {}-{} Default embedded server configuration will be used.", minPort, maxPort);
        }
        return userDefinedPort;
    }
}
