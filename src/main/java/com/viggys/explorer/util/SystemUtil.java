package com.viggys.explorer.util;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

@Slf4j
public class SystemUtil {

    private SystemUtil() {}

    public static final void load() {
        log.info("Loading SystemUtil...");
    }

    private static final String USER_NAME = System.getProperty(Constants.USER_NAME);
    private static final Path USER_HOME = Path.of(System.getProperty(Constants.USER_HOME));
    private static String IP_ADDRESS;
    private static String HOST_NAME;

    static {
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            IP_ADDRESS = inetAddress.getHostAddress();
            HOST_NAME = inetAddress.getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public static String getUserName() {
        return USER_NAME;
    }

    public static Path getUserHome() {
        return USER_HOME;
    }

    public static String getIPAddress() { return IP_ADDRESS; }

    public static String getHostName() { return HOST_NAME; }


}
