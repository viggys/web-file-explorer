package com.viggys.explorer.service;

import com.viggys.explorer.model.request.DeleteRequest;
import com.viggys.explorer.model.request.UpdateRequest;
import com.viggys.explorer.model.response.FileView;
import com.viggys.explorer.model.response.ViewInterface;
import com.viggys.explorer.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@Service
public class FileStorageService implements StorageServiceInterface {

    @Override
    public ViewInterface inspect(Path path) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");
        return new FileView(path);
    }

    @Override
    public ViewInterface download(Path path) {
        Assert.isTrue(path.toFile().isFile(), "Resource is not a file.");
        return new FileView(path);
    }

    @Override
    public ViewInterface upload(Path path) {
        return null;
    }

    public void update(UpdateRequest updateRequest) throws IOException {
        File file = PathUtil.resolvePath(updateRequest.getPath()).toFile();
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            writer.write(updateRequest.getContent());
        }
        finally {
            writer.close();
        }
    }

    @Override
    public void delete(DeleteRequest deleteRequest) throws IOException {
        Path path = PathUtil.resolvePath(deleteRequest.getPath());
        if(path.getFileName().toString().equals(deleteRequest.getFilename())) {
            Files.delete(path);
        }
        else {
            throw new IllegalArgumentException("File name does not match with delete request");
        }
    }
}
