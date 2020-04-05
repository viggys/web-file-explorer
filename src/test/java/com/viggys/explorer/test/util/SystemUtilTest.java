package com.viggys.explorer.test.util;


import com.viggys.explorer.util.SystemUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

public class SystemUtilTest {

    private static final Logger log = LoggerFactory.getLogger(SystemUtilTest.class);

    @Test
//    @Disabled
    public void getSystemProperties() {
        Properties systemProps = System.getProperties();
        systemProps.entrySet().stream().forEach(prop -> {
            log.info("{} :: {}", prop.getKey(), prop.getValue());
        });
    }

    @Test
    public void getIPAddress() throws UnknownHostException {
        String ip = SystemUtil.getIPAddress();
        log.info("IP Address = " + ip);
    }

    @Test
    public void getHostName() throws UnknownHostException {
        String hostName = SystemUtil.getHostName();
        log.info("HostName = " + hostName);
    }
}
