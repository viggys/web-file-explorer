package com.viggys.explorer.controller;

import com.viggys.explorer.service.StorageServiceFactory;
import com.viggys.explorer.service.StorageServiceInterface;
import com.viggys.explorer.view.BrowserView;
import com.viggys.explorer.view.DirectoryView;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

@RestController
public class PathController {

    private static final Logger log = LoggerFactory.getLogger(PathController.class);

    @Autowired private StorageServiceFactory serviceFactory;

    @GetMapping(path = "**",
            produces = MediaType.ALL_VALUE)
    public ResponseEntity inspect(HttpServletRequest request) {
        try{
            String fqPathValue = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            Path path = PathUtil.resolvePath(fqPathValue);
            log.info("path: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            BrowserView view = storageService.inspect(path);

            return view.generateInspectResponse(request, storageService.getTemplateEngine());

        }
        catch(IOException ieo) {
            log.error("Exception in PathController.list :: ", ieo);
            return null;
        }
    }

    @GetMapping(path = "**/download",
            produces = MediaType.ALL_VALUE)
    public ResponseEntity download(HttpServletRequest request) {
        try{
            String fqPathValue = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            Path path = PathUtil.resolvePath(fqPathValue.substring(0,fqPathValue.indexOf("/download")));
            log.info("path: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            BrowserView view = storageService.download(path);

            return view.generateDownloadResponse(request);

        }
        catch(IOException ieo) {
            log.error("Exception in PathController.list :: ", ieo);
            return null;
        }
    }

}
