package com.viggys.explorer.controller;

import com.viggys.explorer.service.StorageServiceFactory;
import com.viggys.explorer.service.StorageServiceInterface;
import com.viggys.explorer.util.PathUtil;
import com.viggys.explorer.view.BrowserView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@RestController
public class PathController {

    @Autowired private StorageServiceFactory serviceFactory;

    @GetMapping(path = "**",
            produces = MediaType.ALL_VALUE)
    public ResponseEntity inspect(HttpServletRequest request,
                                  @RequestParam(required = false, defaultValue = "false") Boolean showHidden) {
        try{
            String fqPathValue = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            Path path = PathUtil.resolvePath(fqPathValue);
            log.info("path: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            BrowserView view = storageService.inspect(path, showHidden);

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
