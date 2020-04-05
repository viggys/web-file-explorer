package com.viggys.explorer.util;

import static com.viggys.explorer.util.Constants.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Path;

public class SystemUtil {

    public static String getUserName() {
        return System.getProperty(USER_NAME);
    }

    public static Path getUserHome() {
        return Path.of(System.getProperty(USER_HOME));
    }

    private static InetAddress getInetAddress() throws UnknownHostException {
        return InetAddress.getLocalHost();
    }

    public static String getIPAddress() throws UnknownHostException {
        return getInetAddress().getHostAddress();
    }

    public static String getHostName() throws UnknownHostException {
        return getInetAddress().getHostName();
    }


}
