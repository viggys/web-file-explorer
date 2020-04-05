package com.viggys.explorer.service;

import com.viggys.explorer.view.BrowserView;
import com.viggys.explorer.view.FileView;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;

import java.nio.file.Path;

@Service
public class FileStorageService implements StorageServiceInterface {

    private static final Logger log = LoggerFactory.getLogger(FileStorageService.class);

    @Autowired @Getter
    private TemplateEngine templateEngine;

    @Override
    public BrowserView inspect(Path path) {
        return null;
    }

    @Override
    public BrowserView download(Path path) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");

        Resource resource = new PathResource(path);
        return new FileView(resource);
    }

    @Override
    public BrowserView upload(Path path) {
        return null;
    }
}
