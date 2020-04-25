package com.viggys.explorer.service;

import com.viggys.explorer.view.BrowserView;
import com.viggys.explorer.view.FileView;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;

import java.nio.file.Path;

@Slf4j
@Service
public class FileStorageService implements StorageServiceInterface {

    @Autowired @Getter
    private TemplateEngine templateEngine;

    @Override
    public BrowserView inspect(Path path, boolean showHidden) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");

        Resource resource = new PathResource(path);
        return new FileView(resource);
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
