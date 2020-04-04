package com.viggys.explorer.util;

import static com.viggys.explorer.util.Constants.*;

import java.nio.file.Path;

public class SystemUtil {

    public static String getUserName() {
        return System.getProperty(USER_NAME);
    }

    public static Path getUserHome() {
        return Path.of(System.getProperty(USER_HOME));
    }


}
