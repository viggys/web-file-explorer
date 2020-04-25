package com.viggys.explorer.test.util;


import com.viggys.explorer.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.net.UnknownHostException;
import java.util.Properties;

@Slf4j
public class SystemUtilTest {

    @Test
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
