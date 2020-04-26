package com.viggys.explorer.util;

import com.viggys.explorer.controller.PathController;
import com.viggys.explorer.model.PathLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
public class PathUtil {

    @CacheEvict(value = "paths")
    public static List<Path> list(Path path) throws IOException {
        Assert.notNull(path, "Path cannot be null.");

        return Files.list(path).collect(Collectors.toList());
    }

    public static String getLabel(Path path) {
        return path.getFileName() != null ? path.getFileName().toString() : "";
    }

    public static PathLink getPathLink(Path path) {
        return PathLink.builder()
                .label(getLabel(path))
                .href(getInspectUrl(path))
                .build();
    }

    public static String getInspectUrl(Path path) {
        String encodedPath = encodeValue(path);
        return linkTo(PathController.class)
                .slash(encodedPath)
                .toUri().toString();
    }

    public static String getDownloadUrl(Path path) {
        String encodedPath = encodeValue(path);
        return linkTo(PathController.class)
                .slash(encodedPath.concat(File.separator).concat("download"))
                .toUri().toString();
    }

    public static Path resolvePath(String path) {
        if(StringUtils.isEmpty(path) || path.equals("/")) {
            return SystemUtil.getUserHome();
        }
        else {
            String decodedPath = decodeValue(path);
            validatePath(decodedPath);
            return Path.of(decodedPath);
        }
    }

    private static void validatePath(String path) {
        Assert.isTrue(Path.of(path).toFile().exists(),"Path [" + path + "] does not exist");
    }

    private static String encodeValue(Path path) {
        StringBuilder encodedPathBuilder = new StringBuilder();
        path.iterator().forEachRemaining(segment -> {
            String encodedSegment = URLEncoder.encode(segment.toString(), StandardCharsets.UTF_8);
            encodedPathBuilder.append(File.separator).append(encodedSegment);
        });
        String encodedPath = encodedPathBuilder.toString();
        log.debug("Encoded Path :: [{}]", encodedPath);
        return encodedPath;
    }

    private static String decodeValue(String value) {
        String decodedPath = URLDecoder.decode(value, StandardCharsets.UTF_8);
        log.debug("Decoded Path :: [{}]", decodedPath);
        return decodedPath;
    }

}
