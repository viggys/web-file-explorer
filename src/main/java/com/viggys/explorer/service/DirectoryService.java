package com.viggys.explorer.service;

import com.viggys.explorer.model.request.AddRequest;
import com.viggys.explorer.model.request.DeleteRequest;
import com.viggys.explorer.model.response.DirectoryView;
import com.viggys.explorer.model.response.ViewInterface;
import com.viggys.explorer.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Slf4j
@Service
public class DirectoryService implements StorageServiceInterface {

    @Override
    public ViewInterface inspect(Path path) throws IOException {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        List<Path> artifactPaths = PathUtil.list(path);
        return new DirectoryView(path, artifactPaths);
    }

    @Override
    public ViewInterface download(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }

    public void create(AddRequest request) throws IOException {
        File file = new File(PathUtil.resolvePath(request.getPath()).toString()
                + File.separator + request.getFilename());
        if(request.isDirectory()) {
            file.mkdir();
        }
        else if(request.isFile()) {
            file.createNewFile();
        }
    }

    @Override
    public ViewInterface upload(Path path) {
        Assert.isTrue(path.toFile().isDirectory(), "Resource is not a directory.");

        return null;
    }

    @Override
    public void delete(DeleteRequest deleteRequest) throws IOException {
        Path path = PathUtil.resolvePath(deleteRequest.getPath());
        if(path.getFileName().toString().equals(deleteRequest.getFilename())) {
            Files.delete(path);
        }
        else {
            throw new IllegalArgumentException("Directory name does not match with delete request");
        }
    }
}
