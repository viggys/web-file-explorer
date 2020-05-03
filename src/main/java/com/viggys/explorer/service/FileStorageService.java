package com.viggys.explorer.service;

import com.viggys.explorer.view.BrowserView;
import com.viggys.explorer.view.FileView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.nio.file.Path;

@Slf4j
@Service
public class FileStorageService implements StorageServiceInterface {

    @Override
    public BrowserView inspect(Path path, boolean showHidden) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");
        return new FileView(path);
    }

    @Override
    public BrowserView download(Path path) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");
        return new FileView(path);
    }

    @Override
    public BrowserView upload(Path path) {
        return null;
    }
}
