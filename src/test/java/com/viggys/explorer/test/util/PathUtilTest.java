package com.viggys.explorer.test.util;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

public class PathUtilTest {

    private static final Logger log = LoggerFactory.getLogger(PathUtilTest.class);

    @Test
    public void list() throws IOException {
        Path path = SystemUtil.getUserHome();
        List<Path> files = PathUtil.list(path);
        log.info("{}", files);
    }

    @Test
    public void getLink() {
        Path path = SystemUtil.getUserHome();
        String link = PathUtil.getInspectUrl(path);
        System.out.println(link);
    }

    @Test
    public void encodeValue() throws MalformedURLException {
        String value = "/Users/vigneshsubramanian/Documents/My Documents";
        Path path = PathUtil.resolvePath(value);
        StringBuilder encodedPathBuilder = new StringBuilder();
        path.iterator().forEachRemaining(segment -> {
            String encodedSegment = URLEncoder.encode(segment.toString(), StandardCharsets.UTF_8);
            encodedPathBuilder.append(File.separator).append(encodedSegment);
            log.info("[{}]", encodedSegment);
        });
        String encodedPath = encodedPathBuilder.toString();
        log.info("Encoded Path = [{}]", encodedPath);
    }
}
