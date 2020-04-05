package com.viggys.explorer.service;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.view.BrowserView;
import com.viggys.explorer.view.DirectoryView;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Service
public class DirectoryService implements StorageServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(DirectoryService.class);

    @Autowired @Getter
    private TemplateEngine templateEngine;

    @Override
    public BrowserView inspect(Path path) throws IOException {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        List<Path> artifactPaths = PathUtil.list(path);
        return new DirectoryView(path, artifactPaths);
    }

    @Override
    public BrowserView download(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }

    @Override
    public BrowserView upload(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }
}
