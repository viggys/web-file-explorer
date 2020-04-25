package com.viggys.explorer.service;

import com.viggys.explorer.view.BrowserView;
import org.thymeleaf.TemplateEngine;

import java.io.IOException;
import java.nio.file.Path;

public interface StorageServiceInterface {

    TemplateEngine getTemplateEngine();

    BrowserView inspect(Path path, boolean showHidden) throws IOException;

    BrowserView download(Path path);

    BrowserView upload(Path path);

}
