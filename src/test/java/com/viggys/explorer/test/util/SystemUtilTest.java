package com.viggys.explorer.test.util;


import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class SystemUtilTest {

    private static final Logger log = LoggerFactory.getLogger(SystemUtilTest.class);

    @Test
    public void getSystemProperties() {
        Properties systemProps = System.getProperties();
        systemProps.entrySet().stream().forEach(prop -> {
            log.info("{} :: {}", prop.getKey(), prop.getValue());
        });
    }
}
