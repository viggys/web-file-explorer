package com.viggys.explorer.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class SpringPropertyUtil {

    @Autowired
    private Environment environment;

    private static SpringPropertyUtil INSTANCE;

    private SpringPropertyUtil() {}

    @PostConstruct
    private void initialize() {
        INSTANCE = this;
    }

    public static String getProperty(String name) {
        return INSTANCE.environment.getProperty(name);
    }
}
