package com.viggys.explorer.service;

import com.viggys.explorer.view.ViewInterface;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageServiceInterface {

    ViewInterface inspect(Path path, boolean showHidden) throws IOException;

    ViewInterface download(Path path);

    ViewInterface upload(Path path);

}
