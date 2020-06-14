package com.viggys.explorer.controller;

import com.viggys.explorer.controller.handler.PathSegment;
import com.viggys.explorer.model.response.ViewInterface;
import com.viggys.explorer.service.StorageServiceFactory;
import com.viggys.explorer.service.StorageServiceInterface;
import com.viggys.explorer.util.Constants;
import com.viggys.explorer.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Controller
@RequestMapping(path = Constants.EXPLORE_CONTROLLER_URI)
public class ExploreController {

    @Autowired private StorageServiceFactory serviceFactory;

    @GetMapping(path = "**",
            produces = MediaType.ALL_VALUE)
    public ModelAndView inspect(HttpServletRequest request,
                                @PathSegment String fqPathValue) {
        try{
            Path path = PathUtil.resolvePath(fqPathValue);
            log.info("INSPECT: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            ViewInterface view = storageService.inspect(path);

            return view.generateInspectResponse(request);
        }
        catch(IOException ieo) {
            log.error("Exception in INSPECT [{}]", fqPathValue);
            ieo.printStackTrace();
            return null;
        }
    }

    @GetMapping(path = "**/download",
            produces = MediaType.ALL_VALUE)
    public ResponseEntity download(HttpServletRequest request,
                                   @PathSegment String fqPathValue) {
        try{
            Path path = PathUtil.resolvePath(fqPathValue.substring(0,fqPathValue.indexOf("/download")));
            log.info("DOWNLOAD: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            ViewInterface view = storageService.download(path);

            return view.generateDownloadResponse(request);
        }
        catch(IOException ieo) {
            log.error("Exception in DOWNLOAD :: ", ieo);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
