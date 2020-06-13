package com.viggys.explorer.test.util;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.List;

@Slf4j
public class PathUtilTest {

    @Test
    public void list() throws IOException {
        Path path = SystemUtil.getUserHome();
        List<Path> files = PathUtil.list(path);
        log.info("{}", files);
    }

    @Test
    public void getLink() {
        Path path = new File("/").toPath(); //SystemUtil.getUserHome();
        String link = PathUtil.getInspectUrl(path);
        System.out.println(link);
    }

    @Test
    public void encodeValue() {
        String value = SystemUtil.getUserHome().toFile().getAbsolutePath() + "/Documents/My Documents";
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
