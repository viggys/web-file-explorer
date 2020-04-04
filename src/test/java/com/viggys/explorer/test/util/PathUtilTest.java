package com.viggys.explorer.test.util;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Stream;

public class PathUtilTest {

    private static final Logger log = LoggerFactory.getLogger(PathUtilTest.class);

    @Test
    public void list() throws IOException {
        Path path = SystemUtil.getUserHome();
        Set<Path> files = PathUtil.list(path);
        log.info("{}", files);
    }
}
