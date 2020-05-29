package com.viggys.explorer.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.viggys.explorer.model.request.AddRequest;
import com.viggys.explorer.model.response.ViewInterface;
import com.viggys.explorer.service.DirectoryService;
import com.viggys.explorer.service.StorageServiceFactory;
import com.viggys.explorer.service.StorageServiceInterface;
import com.viggys.explorer.util.PathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Path;

@Slf4j
@Controller
public class PathController {

    @Autowired private StorageServiceFactory serviceFactory;

    @GetMapping(path = "**",
            produces = MediaType.ALL_VALUE)
    public ModelAndView inspect(HttpServletRequest request/*,
                                @RequestParam(required = false, defaultValue = "false") Boolean showHidden*/) {
        try{
            String fqPathValue = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
            Path path = PathUtil.resolvePath(fqPathValue);
            log.info("INSPECT: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            ViewInterface view = storageService.inspect(path);

            return view.generateInspectResponse(request);

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
            log.info("DOWNLOAD: [{}]", path);

            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            ViewInterface view = storageService.download(path);

            return view.generateDownloadResponse(request);

        }
        catch(IOException ieo) {
            log.error("Exception in PathController.list :: ", ieo);
            return null;
        }
    }

    @PostMapping(path = "/create",
            consumes = MediaType.ALL_VALUE)
    public ResponseEntity create(HttpServletRequest request,
                                 @RequestBody AddRequest addRequest) throws JsonProcessingException {
        try {
            ObjectMapper mapper = new ObjectMapper();
            log.info("CREATE: {}", mapper.writeValueAsString(addRequest));
            Path path = PathUtil.resolvePath(addRequest.getPath());
            DirectoryService storageService = (DirectoryService) serviceFactory.getStorageService(path);
            storageService.create(addRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
