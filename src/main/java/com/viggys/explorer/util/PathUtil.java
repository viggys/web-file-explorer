package com.viggys.explorer.util;

import com.viggys.explorer.controller.PathController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.hateoas.Link;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class PathUtil {

    private static final Logger log = LoggerFactory.getLogger(PathUtil.class);

    @Cacheable(value = "paths")
    public static List<Path> list(Path path) throws IOException {
        Assert.notNull(path, "Path cannot be null.");

        return Files.list(path).collect(Collectors.toList());
    }

    public static String getInspectUrl(Path path) {
        return linkTo(PathController.class)
                .slash(path.toString())
                .toUri().toString();
    }

    public static String getDownloadUrl(Path path) {
        return linkTo(PathController.class)
                .slash(path.toString().concat(File.separator).concat("download"))
                .toUri().toString();
    }

    public static Path resolvePath(String path) {
        if(StringUtils.isEmpty(path) || path.equals("/")) {
            return SystemUtil.getUserHome();
        }
        else {
            validatePath(path);
            return Path.of(path);
        }
    }

    private static void validatePath(String path) {
        Assert.isTrue(Path.of(path).toFile().exists(),"Path does not exist");
    }

}
