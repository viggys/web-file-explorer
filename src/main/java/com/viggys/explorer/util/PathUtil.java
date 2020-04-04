package com.viggys.explorer.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class PathUtil {

    private static final Logger log = LoggerFactory.getLogger(PathUtil.class);

    public static Set<Path> list(Path path) throws IOException {
        return Files.list(path)
                .map(Path::getFileName)
                .collect(Collectors.toSet());
    }

}
