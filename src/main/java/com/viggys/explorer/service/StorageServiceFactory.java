package com.viggys.explorer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.nio.file.Path;

@Component
public class StorageServiceFactory {

    @Autowired private FileStorageService fileStorageService;
    @Autowired private DirectoryService directoryService;

    public StorageServiceInterface getStorageService(Path path) {
        Assert.notNull(path,"Path cannot be null.");
        Assert.isTrue(path.toFile().exists(), "File Not Found.");

        return (path.toFile().isFile()) ? fileStorageService : directoryService;
    }
}
