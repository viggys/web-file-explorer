package com.viggys.explorer.service;

import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.view.ViewInterface;
import com.viggys.explorer.view.DirectoryView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class DirectoryService implements StorageServiceInterface {

    @Override
    public ViewInterface inspect(Path path, boolean showHidden) throws IOException {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        List<Path> artifactPaths = PathUtil.list(path);
        return new DirectoryView(path, artifactPaths, showHidden);
    }

    @Override
    public ViewInterface download(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }

    @Override
    public ViewInterface upload(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }
}
