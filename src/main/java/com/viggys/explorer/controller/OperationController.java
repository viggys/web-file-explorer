package com.viggys.explorer.controller;

import com.viggys.explorer.model.request.AddRequest;
import com.viggys.explorer.model.request.DeleteRequest;
import com.viggys.explorer.model.request.UpdateRequest;
import com.viggys.explorer.service.DirectoryService;
import com.viggys.explorer.service.FileStorageService;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.Path;

@Slf4j
@Controller
@RequestMapping(path = Constants.OPERATION_CONTROLLER_URI)
public class OperationController {

    @Autowired
    private StorageServiceFactory serviceFactory;

    @PostMapping(path = "/create",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(HttpServletRequest request,
                                 @RequestBody AddRequest addRequest) {
        try {
            log.info("CREATE: {}", addRequest);
            Path path = PathUtil.resolvePath(addRequest.getPath());
            DirectoryService storageService = (DirectoryService) serviceFactory.getStorageService(path);
            storageService.create(addRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        catch (Exception e) {
            log.error("Exception in CREATE :: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping(path = "/update",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity update(HttpServletRequest request,
                                 @RequestBody UpdateRequest updateRequest) {
        try {
            log.info("UPDATE: {}", updateRequest);
            Path path = PathUtil.resolvePath(updateRequest.getPath());
            FileStorageService storageService = (FileStorageService) serviceFactory.getStorageService(path);
            storageService.update(updateRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        catch (Exception e) {
            log.error("Exception in UPDATE :: ", e);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping(path = "/delete",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity delete(HttpServletRequest request,
                                 @RequestBody DeleteRequest deleteRequest) {
        try {
            log.info("DELETE: {}", deleteRequest);
            Path path = PathUtil.resolvePath(deleteRequest.getPath());
            StorageServiceInterface storageService = serviceFactory.getStorageService(path);
            storageService.delete(deleteRequest);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch (IllegalArgumentException iae) {
            log.error("Illegal DELETE request :: {}", iae.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (Exception e) {
            log.error("Exception in DELETE :: ", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
